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
		tipos.add(0,new TipoVehiculo("Sedan"));
		tipos.add(1,new TipoVehiculo("Tanque"));
		tipos.add(2,new TipoVehiculo("Moto"));
		tipos.add(3,new TipoVehiculo("Camioneta"));
		tipos.add(4,new TipoVehiculo("Camion"));
		tipos.add(5,new TipoVehiculo("Tren"));
		tipos.add(6,new TipoVehiculo("Metro"));
		tipos.add(7,new TipoVehiculo("Bicicleta"));
		tipos.add(8,new TipoVehiculo("Skate"));
		tipos.add(9,new TipoVehiculo("CityCar"));
		
		vehiculos.add(new Vehiculo("Yaris", tipos.get(0)));
		vehiculos.add(new Vehiculo("Corsa", tipos.get(1)));
		vehiculos.add(new Vehiculo("Vespa", tipos.get(2)));
		vehiculos.add(new Vehiculo("Linea 4", tipos.get(6)));
		vehiculos.add(new Vehiculo("Goliat", tipos.get(4)));
		vehiculos.add(new Vehiculo("Harley", tipos.get(2)));
		vehiculos.add(new Vehiculo("Spark", tipos.get(9)));
		
		logger.info("Se inicializa con "+tipos.size()+" Tipos de Vehiculos");
		logger.info("Se inicializa con "+vehiculos.size()+" Vehiculos");
	}
	
	public List<Vehiculo> obtenerVehiculos(){
		return this.vehiculos;
	}

}
