package ejemplo.ejb;

import java.io.Serializable;
import java.util.Date;

public class MiObjetoEnviado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String asunto;
	private String texto;
	private Date fechaEnviado;
	
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getFechaEnviado() {
		return fechaEnviado;
	}
	public void setFechaEnviado(Date fechaEnviado) {
		this.fechaEnviado = fechaEnviado;
	}
	@Override
	public String toString() {
		return "MiObjetoEnviado [asunto=" + asunto + ", texto=" + texto
				+ ", fechaEnviado=" + fechaEnviado + "]";
	}
	
}
