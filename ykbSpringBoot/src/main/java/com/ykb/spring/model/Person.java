package com.ykb.spring.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ykb.spring.rest.validation.MyStrValid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(description = "Person datası")
public class Person {

    @XmlElement(name = "isim")
    @NotEmpty(message = "isim boş olamaz")
    @Size(min = 2, max = 20, message = "isim 2 ile 20 arasında olamalı")
    @ApiModelProperty(name = "isim", example = "ashdasjdh")
    private String name;

    @Size(min = 3, max = 25, message = "soyisim 3 ile 25 arasında olamalı")
    @NotEmpty(message = "soyisim boş olamaz")
    @MyStrValid(length = 10, message = "surname 10 dan küçük olamaz")
    private String surname;

    @NotEmpty(message = "email boş olamaz")
    @Email(message = "email formatında olmalı")
    @MyStrValid(length = 10, message = "Email 10 dan küçük olamaz")
    private String email;

    private int    age;


    public Person() {
    }


    public Person(final String nameParam,
                  final String surnameParam,
                  final int ageParam) {
        super();
        this.name = nameParam;
        this.surname = surnameParam;
        this.age = ageParam;
    }


    public String getName() {
        return this.name;
    }

    public void setName(final String nameParam) {
        this.name = nameParam;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(final String surnameParam) {
        this.surname = surnameParam;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(final int ageParam) {
        this.age = ageParam;
    }


    public String getEmail() {
        return this.email;
    }


    public void setEmail(final String emailParam) {
        this.email = emailParam;
    }


}
