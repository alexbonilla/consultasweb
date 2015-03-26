package com.iveloper.concesiones.jsf;

import com.iveloper.concesiones.entities.AltaUsuarios;
import com.iveloper.concesiones.jsf.util.JsfUtil;
import com.iveloper.concesiones.jsf.util.JsfUtil.PersistAction;
import com.iveloper.concesiones.beans.AltaUsuariosFacade;
import com.iveloper.concesiones.controllers.AltaUsuariosJpaController;
import com.iveloper.ihsuite.services.security.LoginBean;
import com.iveloper.ihsuite.services.security.LoginBeanUtils;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

@ManagedBean(name = "altaUsuariosController")
@SessionScoped
public class AltaUsuariosController implements Serializable {

    @EJB
    private com.iveloper.concesiones.beans.AltaUsuariosFacade ejbFacade;
    private List<AltaUsuarios> items = null;
    private AltaUsuarios selected;
    private String newpassword;
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    public AltaUsuariosController() {
        try {        
            HttpSession session = LoginBeanUtils.getSession();
            String id = (String) session.getAttribute("usuario");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("consultaswebPU");
            Context c = new InitialContext();
            UserTransaction utx = (UserTransaction) c.lookup("java:comp/UserTransaction");
            
            AltaUsuariosJpaController altaUsuariosController = new AltaUsuariosJpaController(utx, emf);
            selected = altaUsuariosController.findAltaUsuarios(id);
        } catch (NamingException ex) {
            Logger.getLogger(AltaUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AltaUsuarios getSelected() {
        return selected;
    }

    public void setSelected(AltaUsuarios selected) {
        this.selected = selected;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AltaUsuariosFacade getFacade() {
        return ejbFacade;
    }

    public AltaUsuarios prepareCreate() {
        selected = new AltaUsuarios();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AltaUsuariosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AltaUsuariosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AltaUsuariosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AltaUsuarios> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    if (persistAction == PersistAction.UPDATE && newpassword != null && !newpassword.isEmpty()) {
                        selected.setClave(newpassword);
                    }
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<AltaUsuarios> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AltaUsuarios> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AltaUsuarios.class)
    public static class AltaUsuariosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AltaUsuariosController controller = (AltaUsuariosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "altaUsuariosController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof AltaUsuarios) {
                AltaUsuarios o = (AltaUsuarios) object;
                return getStringKey(o.getUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AltaUsuarios.class.getName()});
                return null;
            }
        }

    }

}
