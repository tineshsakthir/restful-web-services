package com.tinesh.rest.webservices.restfulwebservices.user.jpa;

import com.tinesh.rest.webservices.restfulwebservices.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
