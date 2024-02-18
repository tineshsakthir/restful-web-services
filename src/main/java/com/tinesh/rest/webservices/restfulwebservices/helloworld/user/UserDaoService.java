package com.tinesh.rest.webservices.restfulwebservices.helloworld.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UserDaoService {
    private static List<User> users = new ArrayList<>() ;
    static{
        users.add(new User(1, "Tinesh", LocalDate.now().minusYears(20))) ;
        users.add(new User(2, "Thiru", LocalDate.now().minusYears(30))) ;
        users.add(new User(3, "Sudar", LocalDate.now().minusYears(40))) ;
        users.add(new User(4, "Vaishak", LocalDate.now().minusYears(50))) ;
    }
    public List<User> findAll() {
        return users;
    }

    public User findOne(int id){
        Predicate<User> predicate = user-> user.id() == id ;
        return users.stream().filter(predicate).findFirst().get() ;
    }
}
