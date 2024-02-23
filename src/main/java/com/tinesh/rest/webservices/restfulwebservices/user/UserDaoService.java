package com.tinesh.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>() ;
    private static int userCount = 0 ;
    static{
        users.add(new User(++userCount, "Tinesh", LocalDate.now().minusYears(20))) ;
        users.add(new User(++userCount, "Thiru", LocalDate.now().minusYears(30))) ;
        users.add(new User(++userCount, "Sudar", LocalDate.now().minusYears(40))) ;
        users.add(new User(++userCount, "Vaishak", LocalDate.now().minusYears(50))) ;
    }
    public List<User> findAll() {
        return users;
    }

    public User findOne(int id){
        Predicate<User> predicate = user-> user.getId() == id ;
        //here .orElse is used to avoid whitelabel error and exception traceback ,
        //which converts the error into success with null data when there is not such user exist
        return users.stream().filter(predicate).findFirst().orElse(null) ;
    }

    public User save(User user) {
        user.setId(++userCount) ;
        users.add(user) ;
        return user ;
    }

    public void deleteUserById(int id) {
        Predicate<?super User> predicate = user -> user.getId() == id ;
        users.removeIf(predicate) ;
     }
}
