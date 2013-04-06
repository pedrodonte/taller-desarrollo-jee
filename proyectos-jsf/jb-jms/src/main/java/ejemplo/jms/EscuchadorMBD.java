package ejemplo.jms;

import static ejemplo.jms.Constantes.QUEUE;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ManagedBean
@ApplicationScoped
@MessageDriven(name = "EscuchadorMBD", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = QUEUE),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class EscuchadorMBD implements MessageListener {
	
	Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
	
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			logger.info("Procesa Mensaje:");
			logger.info(textMessage.getText());
			
			PushContext pushContext = PushContextFactory.getDefault().getPushContext();  
	        pushContext.push("/notifications", new FacesMessage("Mensaje desde el servidor", textMessage.getText() ));
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
