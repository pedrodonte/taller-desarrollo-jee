package ejemplo.jsf;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import ejemplo.ejb.ColaServiceEJBImpl;

@ManagedBean
public class IndexBean {
	
	@EJB ColaServiceEJBImpl colaServiceEJBImpl;
	
	public void test(ActionEvent actionEvent){
		
		String mensaje = new Date().toString();
		colaServiceEJBImpl.putMessage(mensaje);
	}
	
}