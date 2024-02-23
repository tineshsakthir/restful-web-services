package com.tinesh.rest.webservices.restfulwebservices.versioning;

public class Person2 {

    private String firstname ;
    private String lastname ;

    public Person2(){
        super() ;
    }
    public Person2(String firstname, String lastname) {
        this.firstname = firstname ;
        this.lastname = lastname ;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


}
