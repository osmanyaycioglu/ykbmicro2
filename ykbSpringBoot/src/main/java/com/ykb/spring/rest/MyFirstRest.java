package com.ykb.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ykb.spring.component.MyFirstComponent;
import com.ykb.spring.model.Person;
import com.ykb.spring.model.PersonManager;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

@RestController
public class MyFirstRest {

    @Autowired
    private PersonManager    pm;

    private MyFirstComponent component;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/hello")
    public String hello2() {
        return "hello2";
    }

    @GetMapping("/hello3/{xyz}/{abc}/{qwe}")
    public String hello3(@PathVariable("xyz") final String name,
                         @PathVariable("abc") final String surname,
                         @PathVariable("qwe") final int age) {
        return "hello " + name + " " + surname + " " + age;
    }

    @GetMapping("/hello4")
    public String hello4(@RequestParam("xyz") final String name,
                         @RequestParam("abc") final String surname,
                         @RequestParam("qwe") final int age) {
        return "hello " + name + " " + surname + " " + age;
    }

    @GetMapping("/hello5/{xyz}")
    public String hello5(@PathVariable("xyz") final String name,
                         @RequestParam("abc") final String surname,
                         @RequestParam("qwe") final int age) {
        return "hello5 " + name + " " + surname + " " + age;
    }


    @GetMapping("/hello6/{xyz}")
    public String hello6(@PathVariable("xyz") final String name,
                         @RequestParam("abc") final String surname,
                         @RequestHeader("qwe") final int age) {
        return "hello6 " + name + " " + surname + " " + age;
    }

    @GetMapping(value = "/hello7/{xyz}",
                produces = {
                             MediaType.APPLICATION_JSON_VALUE,
                             MediaType.APPLICATION_XML_VALUE
                })
    public Person hello7(@PathVariable("xyz") final String name,
                         @RequestParam("abc") final String surname,
                         @RequestHeader("qwe") final int age) {
        return new Person(name,
                          surname,
                          age);
    }

    @PostMapping(value = "/hello8/{xyz}",
                 produces = {
                              MediaType.APPLICATION_JSON_VALUE,
                              MediaType.APPLICATION_XML_VALUE
                 },
                 consumes = {
                              MediaType.APPLICATION_JSON_VALUE,
                              MediaType.APPLICATION_XML_VALUE
                 })
    public Person hello8(@RequestBody final Person person) {
        if (person.getName() == null) {
            throw new IllegalArgumentException("person.name null olamaz");
        }
        if (person.getSurname() == null) {
            throw new IllegalStateException("person.surnname null olamaz");
        }

        return new Person("osman",
                          person.getName(),
                          person.getAge());
    }

    @ApiOperation(notes = "test note", value = "Person yaratma")
    @ApiResponses({
                    @ApiResponse(code = 800,
                                 message = "şudur",
                                 examples = @Example(@ExampleProperty(mediaType = "application/json",
                                                                      value = "{osman:1}")))
    })
    @PostMapping(value = "/hello9/{xyz}",
                 produces = {
                              MediaType.APPLICATION_JSON_VALUE,
                              MediaType.APPLICATION_XML_VALUE
                 },
                 consumes = {
                              MediaType.APPLICATION_JSON_VALUE,
                              MediaType.APPLICATION_XML_VALUE
                 })
    public Person hello9(@RequestBody @Validated final Person person) {
        return new Person("osman",
                          person.getName(),
                          person.getAge());
    }

    //    @ExceptionHandler(IllegalArgumentException.class)
    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
    //    public ErrorObj handleException(final IllegalArgumentException iae) {
    //        ErrorObj errorObjLoc = new ErrorObj();
    //        errorObjLoc.setErrorDesc(iae.getMessage());
    //        errorObjLoc.setErrorCause(1157);
    //        return errorObjLoc;
    //    }
    //
    //    @ExceptionHandler(IllegalStateException.class)
    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
    //    public ErrorObj handleException(final IllegalStateException iae) {
    //        ErrorObj errorObjLoc = new ErrorObj();
    //        errorObjLoc.setErrorDesc(iae.getMessage());
    //        errorObjLoc.setErrorCause(1157);
    //        return errorObjLoc;
    //    }


}

