package ejemplo.pf;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.log4j.Logger;

import ejemplo.datos.TipoVehiculo;
import ejemplo.datos.VehiculoServiceEJB;

@FacesConverter("tipoVehiculoConverter")
public class TipoVehiculoConverter implements Converter{
	
	Logger logger = Logger.getLogger(getClass());

	@EJB
	VehiculoServiceEJB vehiculoServiceEJB;
	List<TipoVehiculo> tipos;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		try {
			tipos = vehiculoServiceEJB.obtenerTipos();
			logger.info("getAsObject.string:"+string);
			
//			if (string.trim().equals("")) {
//				return null;
//			} else {
//
//				for (TipoVehiculo tv : tipos) {
//
//					if (tv.getDescripcion() == string) {
//						logger.info("Coinsidencia encontrada.");
//						return tv;
//					}
//				}
//			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("valor nulo:"+string);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		try {
			if (object == null || object.equals("")) {
				return null;
			} else {
				TipoVehiculo tipo = (TipoVehiculo) object;
				return tipo.getDescripcion();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
