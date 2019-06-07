package ru.burmistrov.jms;

import ru.burmistrov.jms.utils.Sender;

public class AppSender {

    public static void main(String[] args) throws Exception {
        Sender sender = new Sender();
        sender.createMessage("Admin", "Admin");
    }
}
