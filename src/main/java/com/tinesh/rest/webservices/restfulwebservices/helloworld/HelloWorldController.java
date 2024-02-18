package com.tinesh.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class HelloWorldController {
  @GetMapping(path = "/hello-world")
  public String helloWorld() {
    return "Hello World";
  }
  @GetMapping(path = "/hello-world-bean")
  public HelloWorldBean helloWorldBean() {
    return new HelloWorldBean("Hello World");
  }

  @GetMapping(path = "/hello-world/path-variable/{name}")
  public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name){
    return new HelloWorldBean(String.format("Hello world , i am %s" , name) ); 
  }
}
