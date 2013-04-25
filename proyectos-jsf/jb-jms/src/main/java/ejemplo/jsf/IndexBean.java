package ejemplo.jsf;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import ejemplo.ejb.QueueAdmin;
import ejemplo.jms.topic.TopicAdmin;

@ManagedBean
public class IndexBean {
	
	@EJB QueueAdmin colaEJB;
	@EJB TopicAdmin topicEJB;
	
	public void msgTexto(ActionEvent actionEvent){
		
		String mensaje = new Date().toString();
		colaEJB.putMessage(mensaje);
		
	}
	
	public void msgObjeto(ActionEvent actionEvent){
		
		colaEJB.putMessage();
		
		topicEJB.putMessage();
		
	}
	
}
