package ejemplo.jms;

import static ejemplo.jms.Constantes.TOPIC;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ejemplo.ejb.MiObjetoEnviado;


/**
 * La intencion de crear este segundo mdb es verificar el uso de topic, ya que los mdb que estan pendientes de los topics actuan
 * como subscriptores al topic.
 * @author pedrodonte
 *
 */
@MessageDriven(name = "SegundoEscuchadorTopicMBD", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = TOPIC),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class SegundoEscuchadorTopicMBD implements MessageListener {

	Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

	public void onMessage(Message message) {
		procesaMensajeObjeto(message);
	}

	private void procesaMensajeObjeto(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message;
		try {
			
			MiObjetoEnviado miObjetoEnviado = (MiObjetoEnviado) objectMessage.getObject();
			logger.info("2do Subscriptor del topic:");
			logger.info("Asunto: "+miObjetoEnviado.getAsunto());
			logger.info("Texto: "+miObjetoEnviado.getTexto());
			logger.info("Fecha: "+miObjetoEnviado.getFechaEnviado());

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
