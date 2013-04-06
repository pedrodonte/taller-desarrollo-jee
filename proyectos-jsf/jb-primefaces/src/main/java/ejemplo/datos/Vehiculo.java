package ejemplo.datos;

import java.io.Serializable;

public class Vehiculo implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private String modelo;
	private TipoVehiculo tipoVehiculo;
	
	public Vehiculo(){}

	public Vehiculo(String modelo, TipoVehiculo tipoVehiculo) {
		super();
		this.modelo = modelo;
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result
				+ ((tipoVehiculo == null) ? 0 : tipoVehiculo.hashCode());
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
		Vehiculo other = (Vehiculo) obj;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (tipoVehiculo == null) {
			if (other.tipoVehiculo != null)
				return false;
		} else if (!tipoVehiculo.equals(other.tipoVehiculo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehiculo [modelo=" + modelo + ", tipoVehiculo=" + tipoVehiculo
				+ "]";
	}
	
}
