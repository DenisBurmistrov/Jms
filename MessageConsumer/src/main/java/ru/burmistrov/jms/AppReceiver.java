package ru.burmistrov.jms;

import ru.burmistrov.jms.storage.UserList;
import ru.burmistrov.jms.utils.Consumer;

public class AppReceiver {

    public static void main(String[] args) throws Exception {
        UserList userList = new UserList();
        Consumer receiver = new Consumer();

        receiver.consumeAndCreateUser(userList.getUsers());
        System.out.println("USERS: " + userList.getUsers());
    }
}
