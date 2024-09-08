package com.example.trainticket.repository;

import com.example.trainticket.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private static int userCounter = 0;
    private HashMap<Integer, User> users = new HashMap<>();

    public User getUserById(int id){
        return users.get(id);
    }

    public User getUserByData(String firstName, String lastName, String email){
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            if (entry.getValue().getFirstName().equals(firstName)
                    && entry.getValue().getLastName().equals(lastName)
                    && entry.getValue().getEmail().equals(email)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public User findOrCreate(String firstName, String lastName, String email){
        User user = getUserByData(firstName, lastName, email);
        if(user != null){
            return user;
        }

        user = new User(firstName, lastName, email);
        user.setId(generateId());

        return user;
    }

    int generateId(){
        return ++userCounter;
    }

}
