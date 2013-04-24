package ejemplo.ejb;

import static ejemplo.jms.Constantes.FACTORY;
import static ejemplo.jms.Constantes.QUEUE;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
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
	
	public void putMessage() {
		
		MessageProducer messageProducer;
		ObjectMessage objectMessage;
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			messageProducer = session.createProducer(queue);
			objectMessage = session.createObjectMessage();
			
			MiObjetoEnviado objetoEnviado = new MiObjetoEnviado();
			objetoEnviado.setAsunto("Saludos");
			objetoEnviado.setTexto("Buenos días esto es un objeto enviado en una cola jms.");
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
