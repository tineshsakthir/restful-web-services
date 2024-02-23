package com.tinesh.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ManyToAny;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private int id ;
    @Size(min = 6)
    private String description ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user ;

    public Post() {
    }

    public Post(int id, String description, User user) {
        this.id = id;
        this.description = description;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
