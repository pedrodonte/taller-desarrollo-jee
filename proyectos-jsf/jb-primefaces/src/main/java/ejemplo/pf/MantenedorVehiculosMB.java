package ejemplo.pf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import ejemplo.datos.Vehiculo;
import ejemplo.datos.VehiculoServiceEJB;

@ManagedBean
@ViewScoped
public class MantenedorVehiculosMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB VehiculoServiceEJB registroServiceEJB;

	Logger logger = Logger.getLogger(getClass());

	public enum ModosEdicion {
		OFF, EDIT, NEW
	};
	
	private boolean mostrarFormulario = false;
	private ModosEdicion modo = ModosEdicion.OFF;

	private List<Vehiculo> registros = new ArrayList<Vehiculo>();
	private Vehiculo registroSeleccionado = new Vehiculo();
	private Vehiculo registroEnEdicion = new Vehiculo();

	@PostConstruct
	public void inicializarVariablesInstancia() {
		try {
			registros = registroServiceEJB.obtenerVehiculos();
			logger.debug(registros.size() + " registros de Usuario.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doHabilitarEdicion(ActionEvent event) {
		setMostrarFormulario(true);
		setModo(ModosEdicion.EDIT);
	}

	public void doVerRegistroFormulario(ActionEvent event) {
		try {
			setRegistroEnEdicion((Vehiculo) registroSeleccionado.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		setMostrarFormulario(true);
		setModo(ModosEdicion.OFF);
	}

	/**ESTE METODO NO APLICA PARA EL MANTENEDOR DE PERFILES, YA QUE NO SE PERMITE CREAR NUEVOS.*/
	public void doNuevoRegistroFormulario(ActionEvent event) {
		setRegistroEnEdicion(new Vehiculo());
		setMostrarFormulario(true);
		setModo(ModosEdicion.NEW);
	}

	public void doEditarRegistroFormulario(ActionEvent event) {
		try {
			setRegistroEnEdicion((Vehiculo) registroSeleccionado.clone());
			logger.debug("Editando " + getRegistroEnEdicion());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		setMostrarFormulario(true);
		setModo(ModosEdicion.EDIT);
	}

	public void doGuardarRegistroFormulario(ActionEvent event) {
		//boolean codExitoOperacion = false;
		try {
			logger.debug("Guardano Registro: " + registroEnEdicion);
			if (modo.equals(ModosEdicion.EDIT)) {
				//TODO
			} else {
				logger.debug("Nada con Registro: " + registroEnEdicion);
			}
			setMostrarFormulario(false);
			setModo(ModosEdicion.OFF);
			inicializarVariablesInstancia();
			//codExitoOperacion = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//TODO cuando termina la operacion se debe retornar un codigo de operacion (codExitoOperacion)
	}

	public void doCancelarRegistroFormulario(ActionEvent event) {
		setMostrarFormulario(false);
		setModo(ModosEdicion.OFF);
	}

	public boolean isMostrarFormulario() {
		return mostrarFormulario;
	}

	public void setMostrarFormulario(boolean mostrarFormulario) {
		this.mostrarFormulario = mostrarFormulario;
	}

	public ModosEdicion getModo() {
		return modo;
	}

	public void setModo(ModosEdicion modo) {
		this.modo = modo;
	}

	public List<Vehiculo> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Vehiculo> registros) {
		this.registros = registros;
	}


	public Vehiculo getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(Vehiculo registroSeleccionado) {
		logger.debug("Registro Seleccionado:"+registroSeleccionado);
		this.registroSeleccionado = registroSeleccionado;
	}

	public Vehiculo getRegistroEnEdicion() {
		return registroEnEdicion;
	}

	public void setRegistroEnEdicion(Vehiculo registroEnEdicion) {
		this.registroEnEdicion = registroEnEdicion;
	}

}
