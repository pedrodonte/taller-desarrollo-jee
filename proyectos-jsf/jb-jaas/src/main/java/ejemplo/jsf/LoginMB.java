package ejemplo.jsf;

import static org.jboss.crypto.CryptoUtil.BASE64_ENCODING;
import static org.jboss.crypto.CryptoUtil.createPasswordHash;

import java.io.Serializable;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

@ManagedBean
@ViewScoped
public class LoginMB implements Serializable {

	private static final long serialVersionUID = 123L;

	private static final String FORWARD_TO_DASHBOARD = "/protected/dashboard.xhtml?faces-redirect=true";

	private String cpoContrasena = "";
	private String cpoRunDV = "";

	Logger logger = Logger.getLogger(getClass());

	public void doLogin(ActionEvent actionEvent) {
		try {

			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletRequest httpServletRequest = (HttpServletRequest) facesContext
					.getExternalContext().getRequest();

			// Aqui se encripta la contraseña ingresada
			cpoContrasena = createPasswordHash("SHA-256", BASE64_ENCODING,
					null, null, cpoContrasena);

			// Se realiza la autenticacion en caso de fallar lanza la excepcion
			httpServletRequest.login(cpoRunDV, cpoContrasena);

			redireccionar(FORWARD_TO_DASHBOARD);

		} catch (Exception e) {
			logger.error("ErrorGrave", e);
		}
	}


	public void doLogout(ActionEvent actionEvent) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			HttpServletRequest httpServletRequest = (HttpServletRequest) facesContext
					.getExternalContext().getRequest();

			httpServletRequest.getSession().invalidate();
			httpServletRequest.logout();
			redireccionar(FORWARD_TO_DASHBOARD);
		} catch (Exception e) {
			logger.error("ErrorGrave", e);
		}
	}
	
	private void redireccionar(String forwardToDashboard) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		NavigationHandler nh = facesContext.getApplication()
				.getNavigationHandler();
		nh.handleNavigation(facesContext, null, forwardToDashboard);
	}


	public String getCpoContrasena() {
		return cpoContrasena;
	}

	public void setCpoContrasena(String cpoContrasena) {
		this.cpoContrasena = cpoContrasena;
	}

	public String getCpoRunDV() {
		return cpoRunDV;
	}

	public void setCpoRunDV(String cpoRunDV) {
		this.cpoRunDV = cpoRunDV;
	}

}
