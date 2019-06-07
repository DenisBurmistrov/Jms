package ru.burmistrov.jms.utils;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.burmistrov.jms.entity.User;

import javax.jms.*;
import java.util.List;

public class Consumer implements ExceptionListener {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    public void consumeAndCreateUser(List<User> users) throws InterruptedException {
        Runnable recTask = () -> {
            try {
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

                Connection connection = connectionFactory.createConnection();
                connection.start();
                connection.setExceptionListener(this);
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Destination destination = session.createQueue("messages");
                MessageConsumer consumer = session.createConsumer(destination);
                Message message = consumer.receive(4000);

                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();
                    logger.info("Received TextMessage object: " + text);
                    String[] args = text.split(" ");
                    User user = new User();
                    user.setUsername(args[0]);
                    user.setPassword(args[1]);
                    users.add(user);

                } else {
                    logger.info("Received other object type with message: " + message);
                }
                consumer.close();
                session.close();
                connection.close();


            } catch (JMSException e) {
                logger.error("Consumer consumeAndCreateUser method error", e);
            }
        };
        Thread thread = new Thread(recTask);
        thread.start();
        thread.join();
    }

    @Override
    public void onException(JMSException exception) {
        logger.error("Recieve error occured.");
    }
}
