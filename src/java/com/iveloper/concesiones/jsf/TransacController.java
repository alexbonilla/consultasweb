package com.iveloper.concesiones.jsf;

import com.iveloper.concesiones.entities.Transac;
import com.iveloper.concesiones.jsf.util.JsfUtil;
import com.iveloper.concesiones.jsf.util.JsfUtil.PersistAction;
import com.iveloper.concesiones.beans.TransacFacade;
import com.iveloper.concesiones.controllers.TransacJpaController;
import com.iveloper.ihsuite.services.security.LoginBeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
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

@ManagedBean(name = "transacController")
@SessionScoped
public class TransacController implements Serializable {

    @EJB
    private com.iveloper.concesiones.beans.TransacFacade ejbFacade;
    private List<Transac> items = null;
    private Transac selected;

    private int clinumcl;
    private Date startDate;
    private Date endDate;

    private TransacJpaController transacJpaController;

    public int getClinumcl() {
        return clinumcl;
    }

    public void setClinumcl(int clinumcl) {
        this.clinumcl = clinumcl;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public TransacController() {
        HttpSession session = LoginBeanUtils.getSession();

        clinumcl = (int) session.getAttribute("clinumcl");

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("consultaswebPU");
            Context c = new InitialContext();
            UserTransaction utx = (UserTransaction) c.lookup("java:comp/UserTransaction");
            transacJpaController = new TransacJpaController(utx, emf);

        } catch (NamingException ex) {
            Logger.getLogger(TransacController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Transac getSelected() {
        return selected;
    }

    public void setSelected(Transac selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TransacFacade getFacade() {
        return ejbFacade;
    }

    public Transac prepareCreate() {
        selected = new Transac();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TransacCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TransacUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TransacDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Transac> getItems() {
        if (items == null) {
//            items = getFacade().findAll();
            setInitialDateRange();
            items = getItemsByDateRange();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
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

    public List<Transac> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Transac> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Transac.class)
    public static class TransacControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TransacController controller = (TransacController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "transacController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Transac) {
                Transac o = (Transac) object;
                return getStringKey(o.getTraid());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Transac.class.getName()});
                return null;
            }
        }

    }

    /*
     Funciones definidas para el portal de usuarios
     */
    public void setInitialDateRange() {        
        this.endDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(this.endDate);
        c.add(Calendar.DATE, -10);
        this.startDate = c.getTime();
    }

    public List<Transac> getItemsByDateRange() {

        if (clinumcl != 0 && startDate != null && endDate != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(endDate);
            c.add(Calendar.DATE, 1);

            Collection transacColl = transacJpaController.findByTracliAndDateRange(clinumcl, startDate, c.getTime());
            if (transacColl instanceof List) {
                items = (List) transacColl;
            } else {
                items = new ArrayList(transacColl);
            }
            Logger.getLogger(TransacController.class.getName()).log(Level.INFO, "Devolviendo resultados para cliente: " + clinumcl);
        }

        return items;
    }
}
