package ejemplo.pf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

import ejemplo.datos.TipoVehiculo;
import ejemplo.datos.VehiculoServiceEJB;

@ManagedBean
@ViewScoped
public class TestConverterMB implements Serializable {

	private static final long serialVersionUID = 12345L;

	@EJB
	VehiculoServiceEJB registroServiceEJB;

	Logger logger = Logger.getLogger(getClass());

	MensajesMB mensajesMB = new MensajesMB();

	private List<TipoVehiculo> tiposVehiculos = new ArrayList<>();
	private TipoVehiculo registroSeleccionado = new TipoVehiculo();
	
	public void doSeleccionaItem(ValueChangeEvent changeEvent){
		registroSeleccionado = (TipoVehiculo) changeEvent.getNewValue();
		logger.info("RegSele:"+registroSeleccionado);
	}

	@PostConstruct
	public void inicializarVariablesInstancia() {
		try {
			tiposVehiculos = registroServiceEJB.obtenerTipos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<TipoVehiculo> getTiposVehiculos() {
		return tiposVehiculos;
	}

	public void setTiposVehiculos(List<TipoVehiculo> tiposVehiculos) {
		this.tiposVehiculos = tiposVehiculos;
	}

	public TipoVehiculo getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(TipoVehiculo registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}

}
