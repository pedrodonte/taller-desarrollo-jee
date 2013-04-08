package ejemplo.datos;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import org.apache.log4j.Logger;

@Singleton
public class VehiculoServiceEJB {

	Logger logger = Logger.getLogger(getClass());

	List<TipoVehiculo> tipos = new ArrayList<>();
	List<Vehiculo> vehiculos = new ArrayList<>();

	@PostConstruct
	public void init() {
		tipos.add(0, new TipoVehiculo("Sedan"));
		tipos.add(1, new TipoVehiculo("Tanque"));
		tipos.add(2, new TipoVehiculo("Moto"));
		tipos.add(3, new TipoVehiculo("Camioneta"));
		tipos.add(4, new TipoVehiculo("Camion"));
		tipos.add(5, new TipoVehiculo("Tren"));
		tipos.add(6, new TipoVehiculo("Metro"));
		tipos.add(7, new TipoVehiculo("Bicicleta"));
		tipos.add(8, new TipoVehiculo("Skate"));
		tipos.add(9, new TipoVehiculo("CityCar"));

		vehiculos.add(new Vehiculo(1, "Yaris", tipos.get(0)));
		vehiculos.add(new Vehiculo(2, "Corsa", tipos.get(1)));
		vehiculos.add(new Vehiculo(3, "Vespa", tipos.get(2)));
		vehiculos.add(new Vehiculo(4, "Linea 4", tipos.get(6)));
		vehiculos.add(new Vehiculo(5, "Goliat", tipos.get(4)));
		vehiculos.add(new Vehiculo(6, "Harley", tipos.get(2)));
		vehiculos.add(new Vehiculo(7, "Spark", tipos.get(9)));

		logger.info("Se inicializa con " + tipos.size() + " Tipos de Vehiculos");
		logger.info("Se inicializa con " + vehiculos.size() + " Vehiculos");
	}

	public List<Vehiculo> obtenerVehiculos() {
		return this.vehiculos;
	}

	public void actualizarVehiculo(Vehiculo vehiculo) throws Exception {
		boolean ejecutaOperacion = false;
		logger.info("Actualizando registro");

		eliminarRegistro(vehiculo);

		vehiculos.add(vehiculo);
		imprimirVehiculos();
		ejecutaOperacion = true;

		if (!ejecutaOperacion) {
			throw new Exception("No se encuentra el registro para eliminar.");
		}
	}

	private void imprimirVehiculos() {
		for (Vehiculo v : vehiculos) {
			logger.info(v);
		}
	}

	private void eliminarRegistro(Vehiculo vehiculo) {
		for (Vehiculo v : vehiculos) {
			logger.info("Evaluando:" + v);

			if (v.getIdVehiculo() == vehiculo.getIdVehiculo()) {
				vehiculos.remove(v);
				break;
			}
		}
	}
	
	public List<TipoVehiculo> obtenerTipos(){
		return tipos;
	}

}
