package ru.burmistrov.jms;

import ru.burmistrov.jms.storage.UserList;
import ru.burmistrov.jms.utils.Receiver;

public class AppReceiver {

    public static void main(String[] args) throws Exception {
        UserList userList = new UserList();
        Receiver receiver = new Receiver();

        receiver.receiveAndCreateUser(userList.getUsers());
        System.out.println("USERS: " + userList.getUsers());
    }
}
