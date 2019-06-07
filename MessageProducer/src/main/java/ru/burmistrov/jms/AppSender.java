package ru.burmistrov.jms;

import ru.burmistrov.jms.utils.Producer;

public class AppSender {

    public static void main(String[] args) throws Exception {
        Producer producer = new Producer();
        producer.createMessage("Admin", "Admin");
    }
}
