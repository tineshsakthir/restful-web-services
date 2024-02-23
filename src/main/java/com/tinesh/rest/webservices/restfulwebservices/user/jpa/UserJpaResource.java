package com.tinesh.rest.webservices.restfulwebservices.user.jpa;

import com.tinesh.rest.webservices.restfulwebservices.user.Post;
import com.tinesh.rest.webservices.restfulwebservices.user.User;
import com.tinesh.rest.webservices.restfulwebservices.user.UserNotFoundException;
import com.tinesh.rest.webservices.restfulwebservices.user.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    private final UserRepository userRepository ;
    private final PostRepository postRepository ;

    @Autowired
    public UserJpaResource(UserRepository userRepository , PostRepository postRepository){
        this.userRepository = userRepository ;
        this.postRepository = postRepository ;
    }
    @GetMapping("/jpa/users")
    public List<User> allUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> findUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id) ;
        //here findOne will return null when no such user exit with the queried id .
        // by providing that to the view , not it will not give the white label error


        // So with that, i am throwing a user defined exception

        if(user.isEmpty()) {
            throw new UserNotFoundException("Not Found");
            //The above will again provide the white label error with the exception stack trace
            //TO remove the exception trace we have to disable the devtools (founded by  ranga on step 11 section 8)
        }
//        return user ;  before
        /**
         * now after adding the below code, the user is able to knows how to go to get the all the users
         * and this is standard for all users retrievlals and now we are adding attributes with(not to) the
         * user entity without affecting affecting it's structure
         * so that's why we are using entitymodel .it is a data structure like list, but specifically used for links buildings in hateos.....
         * **/
        EntityModel<User> entityModel = EntityModel.of(user.get()) ;
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).allUsers() );
        entityModel.add(link.withRel("all-users")) ;
        return entityModel ;

        /**
         * Here i am returning a entity model because i am following the principle of HAL based model architecture
         * So that's the reason for creating this type of entity model and adding the entity to it also attaching
         * the functionalities that can be can made by the user(Here firing the "/users" url )
         * **/
    }

    @PostMapping("/jpa/users")
    //Visit customizedResponseEntityException class more detailed notes for the @Valid annotation
    public ResponseEntity<User> addUser( @RequestBody @Valid User user){
       User savedUser =  userRepository.save(user) ;
       //Here when the creation operation is done, it should return "201" as response code .
        // But currently the response code comes as "200(200 to 299 says intimates us just the success of  our operation ,
        // but  it is not specific towards the success of creation operation)" .
        //because as a developer i have to follow those constraints in a real time applications
        //so i am explicitely converting the response code as below and i am also sending the
        // created user's location in the response body
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri() ;

        //THe below is the sending response status as "201" without uri of created location
//        return ResponseEntity.created(null).build();

        //The below is the sending of the response status as "201" with the uri of the created location
        return ResponseEntity.created(location).build() ;

        // the above is success at 11:19 pm , 19-feb-2024
    }


    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id) ;
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveUserPosts(@PathVariable int id){
        Optional<User> user = userRepository.findById(id) ;
        if(user.isEmpty()){
            throw new UserNotFoundException("Not Found");
        }
        return user.get().getPostList() ;
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> retrieveUserPosts(@RequestBody Post post,@PathVariable int id){
        Optional<User> user = userRepository.findById(id) ;
        if(user.isEmpty()){
            throw new UserNotFoundException("Not Found");
        }
        post.setUser(user.get());
        Post savedPost =postRepository.save(post) ;
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri() ;
        return ResponseEntity.created(location).build() ;
    }
}
