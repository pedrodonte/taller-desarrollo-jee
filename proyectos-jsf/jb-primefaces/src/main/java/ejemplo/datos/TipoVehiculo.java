package ejemplo.datos;

import java.io.Serializable;

public class TipoVehiculo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String descripcion;
	
	public TipoVehiculo(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
