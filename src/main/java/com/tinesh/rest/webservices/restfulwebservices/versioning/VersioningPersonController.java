package com.tinesh.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
    /**
     * Here i am using version analogy to make changes in the api that does not break the
     * code of the users of the api suddenly .
     * By this versioning mechanisms we can make changes to our api code and creating it as new version and releasing it
     * but the user will use the old version without any problem and they will change
     * to the newer version if they want obviously in the future.....
     *
     *
     * Types of versioning :
     * 1. URI versioning - used in Twitter
     * 2. Request Parameter - used in Amazon
     * 3. Header - used in Microsoft
     * 4. Media Type - used in GitHub
     *
     * **/


    // below two functions comes under "URI versioning"
    @RequestMapping("/v1/person")
    public Person1 version1(){
        return new Person1("Aadhiv Kumaran") ;
    }

    @RequestMapping("/v2/person")
    public Person2 version2(){
        return new Person2("Aadhiv" , "Kumaran") ;
    }


    //below two are Request Parameter versioning

    @RequestMapping(value = "person" , params = "version=1")
    public Person1 getFirstVersionOfPersonRequestParameter(){
        return new Person1("Jeff Bezos") ;
    }
    @RequestMapping(value = "person" , params = "version=2")
    public Person2 getSecondVersionOfPersonRequestParameter(){
        return new Person2("Jeff" , "Bezos") ;
    }


    //By using header object inside the request object, the user will request for a particular version
    @RequestMapping(value = "person" , headers = "X-API-VERSION=1")
    public Person1 getFirstVersionOfPersonUsingHeaderValues(){
        return new Person1("Adi Poli") ;
    }

    @RequestMapping(value = "person" , headers = "X-API-VERSION=2")
    public Person2 getSecondVersionOfPersonUsingHeaderValues(){
        return new Person2("Adi" , "Poli") ;
    }

    //The below two functions implementation versioning based on the "ACCEPT" header value
    @RequestMapping(value = "person" , produces = "application/vnd.company.app-v1+json")
    public Person1 getFirstVersionOfPersonUsingAcceptHeaderValues(){
        return new Person1("Adi Poli") ;
    }

    @RequestMapping(value = "person" , produces = "application/vnd.company.app-v2+json")
    public Person2 getSecondVersionOfPersonUsingAcceptHeaderValues(){
        return new Person2("Adi" , "Poli") ;
    }


    /**
     * Factors to consider before using any of the above versioning techniques
     *
     * 1. URI Pollution (in uri and parameter versioning)
     * 2. Misuse of HTTP Headers(in headers and media type versioning)
     * 3. Caching (in uri and parameter versioning(just need to see the url
     *    for caching but in other two types we have to see the headers also)
     * 4. Executing in browsers?
     * 5.Summary - No perfect Versioning solution - our wish to anyone,
     *    but it should be consistent among the entire project
     **/


}
