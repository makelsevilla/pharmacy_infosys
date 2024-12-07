package org.example;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private final List<User> users = new ArrayList<>();

    public UserManager() {
        // get the users in csv file
        users.add(new User("pharmacist", "1234", "Pharmacist"));
        users.add(new User("assistant", "1234", "Assistant"));
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                return user;
            }
        }
        return null;
    }
}
