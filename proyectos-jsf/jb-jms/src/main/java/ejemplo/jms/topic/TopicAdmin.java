package ejemplo.jms.topic;

import static ejemplo.jms.Constantes.FACTORY;
import static ejemplo.jms.Constantes.TOPIC;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import javax.jms.Session;

import ejemplo.ejb.MiObjetoEnviado;

@Stateless
public class TopicAdmin {
	@Resource(mappedName = FACTORY)
	ConnectionFactory connectionFactory;

	@Resource(mappedName = TOPIC)
	Topic topic;

	public void putMessage() {

		MessageProducer messageProducer;
		ObjectMessage objectMessage;
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			messageProducer = session.createProducer(topic);

			objectMessage = session.createObjectMessage();

			MiObjetoEnviado objetoEnviado = new MiObjetoEnviado();
			objetoEnviado.setAsunto("Saludos");
			objetoEnviado
					.setTexto("Buenos días esto es un objeto enviado en una cola jms.");
			objetoEnviado.setFechaEnviado(new Date());

			objectMessage.setObject(objetoEnviado);
			messageProducer.send(objectMessage);

			messageProducer.close();
			session.close();
			connection.close();

		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
