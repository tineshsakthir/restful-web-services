package com.tinesh.rest.webservices.restfulwebservices.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Locale;


@RestController
public class HelloWorldController {


  /*
  * The below MessageSource is used to handle different language types and
  *  sends the response to the user in
  * different languages by taking different languages definitions from properties files.
  * */
  private final MessageSource messageSource ;
  public HelloWorldController(MessageSource messageSource){
    this.messageSource = messageSource ;
  }
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


  @GetMapping(path = "/hello-world-internationalized")
  public String helloWorldInternationalized() {

    Locale locale = LocaleContextHolder.getLocale();
    //THe above Locale function in the spring provides way to parse the request object and finds the
    //acceptable language and provides it to the Locale
    return messageSource.getMessage("good.morning.message",null,"Default Message", locale) ;
//    return "Hello World";
  }


  
}
