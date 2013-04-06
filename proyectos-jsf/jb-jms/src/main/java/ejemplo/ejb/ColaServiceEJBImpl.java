package ejemplo.ejb;

import static ejemplo.jms.Constantes.*;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@Stateless
public class ColaServiceEJBImpl {

	@Resource(mappedName = FACTORY)
	ConnectionFactory connectionFactory;

	@Resource(mappedName = QUEUE)
	Queue queue;

	public void putMessage(String mensaje) {
		MessageProducer messageProducer;
		TextMessage textMessage;
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			messageProducer = session.createProducer(queue);
			textMessage = session.createTextMessage();
			
			textMessage.setText(mensaje);
			messageProducer.send(textMessage);
			
			messageProducer.close();
			session.close();
			connection.close();
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	
}
