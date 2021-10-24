package com.sparta.dw.northwindrest.dtos;

import javax.persistence.*;



@Entity
public class EmployeeDTO2 {

    @Id
    private Integer id;
    private String name;
    private String title;
    private String country;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName, String lastName, String titleOfCourtesy) {this.name = titleOfCourtesy +" "+ firstName + " "+ lastName;} // this allows all of the name components to be on the same line

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}