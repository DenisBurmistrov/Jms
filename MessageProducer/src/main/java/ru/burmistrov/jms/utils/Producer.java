package ru.burmistrov.jms.utils;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class Producer {
    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    public void createMessage(String username, String password){
        Runnable sendTask = () -> {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = null;
            try {
                connection = connectionFactory.createConnection();
                connection.start();

                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Destination destination = session.createQueue("messages");
                MessageProducer producer = session.createProducer(destination);

                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                String text = username + " " + password;
                TextMessage message = session.createTextMessage(text);
                logger.info("Sent message: " + message);

                producer.send(message);
                session.close();
                connection.close();
            } catch (JMSException e) {
                logger.error("Producer createMessage method error", e);
            }
        };
        new Thread(sendTask).start();
    }
}
