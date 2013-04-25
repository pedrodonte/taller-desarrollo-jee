package ejemplo.jms;

import static ejemplo.jms.Constantes.TOPIC;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.faces.bean.ApplicationScoped;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ejemplo.ejb.MiObjetoEnviado;

@ApplicationScoped
@MessageDriven(name = "EscuchadorTopicMBD", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = TOPIC),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class EscuchadorTopicMBD implements MessageListener {

	Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

	public void onMessage(Message message) {
		procesaMensajeObjeto(message);
	}

	private void procesaMensajeObjeto(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message;
		try {
			
			MiObjetoEnviado miObjetoEnviado = (MiObjetoEnviado) objectMessage.getObject();
			logger.info("Procesa Mensaje(TOPIC):");
			logger.info(miObjetoEnviado.toString());

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
