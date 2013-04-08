package ejemplo.pf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

public class MensajesMB {

	private static final String INFO = "Información";
	private static final String ALERTA = "Alerta";
	private static final String ERROR = "Error";
	private static final String FATAL = "Grave";

	public void mensajeStringInfo(String mensajeString) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, INFO,
						mensajeString));
	}

	public void mensajeStringWarn(String mensajeString) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, ALERTA,
						mensajeString));
	}

	public void mensajeStringError(String mensajeString) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR,
						mensajeString));
	}

	public void mensajeStringFatal(String mensajeString) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, FATAL,
						mensajeString));
	}

	public void devolverParametro(String nombreParametro, Object valorParamtro) {
		RequestContext.getCurrentInstance().addCallbackParam(nombreParametro,
				valorParamtro);
	}

}
