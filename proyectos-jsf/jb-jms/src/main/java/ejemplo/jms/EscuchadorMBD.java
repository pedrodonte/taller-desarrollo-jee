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
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ejemplo.ejb.MiObjetoEnviado;

@ManagedBean
@ApplicationScoped
@MessageDriven(name = "EscuchadorMBD", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = QUEUE),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class EscuchadorMBD implements MessageListener {

	Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			procesaMensajeTexto(message);
		} else if (message instanceof ObjectMessage) {
			procesaMensajeObjeto(message);
		}
	}

	private void procesaMensajeObjeto(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message;
		try {
			MiObjetoEnviado miObjetoEnviado = (MiObjetoEnviado) objectMessage.getObject();
			logger.info("Procesa Mensaje:");
			logger.info(miObjetoEnviado.toString());

			PushContext pushContext = PushContextFactory.getDefault()
					.getPushContext();
			pushContext.push("/notifications", new FacesMessage(
					miObjetoEnviado.getAsunto(), miObjetoEnviado.getTexto()));

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private void procesaMensajeTexto(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			logger.info("Procesa Mensaje de Texto:");
			logger.info(textMessage.getText());

			PushContext pushContext = PushContextFactory.getDefault()
					.getPushContext();
			pushContext.push("/notifications", new FacesMessage(
					"Mensaje desde el servidor", textMessage.getText()));

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
