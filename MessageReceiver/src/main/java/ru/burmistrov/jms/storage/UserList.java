package ru.burmistrov.jms.storage;

import lombok.Data;
import ru.burmistrov.jms.entity.User;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserList {

    private List<User> users;

    public UserList() {
        users = new ArrayList<>();
    }
}
