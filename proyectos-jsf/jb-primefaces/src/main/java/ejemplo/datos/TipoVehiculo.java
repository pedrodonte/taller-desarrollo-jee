package ejemplo.datos;

import java.io.Serializable;

public class TipoVehiculo implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	
	public static int SECUENCIA = 0;
	
	private int idTipoVehiculo;
	private String descripcion;
	
	public TipoVehiculo(String descripcion) {
		super();
		this.idTipoVehiculo = ++SECUENCIA;
		this.descripcion = descripcion;
	}

	public TipoVehiculo() {
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoVehiculo [idTipoVehiculo=" + idTipoVehiculo
				+ ", descripcion=" + descripcion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + idTipoVehiculo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoVehiculo other = (TipoVehiculo) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idTipoVehiculo != other.idTipoVehiculo)
			return false;
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public int getIdTipoVehiculo() {
		return idTipoVehiculo;
	}

	public void setIdTipoVehiculo(int idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
	}
	

}
