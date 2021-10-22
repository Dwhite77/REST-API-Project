package com.sparta.dw.northwindrest.dtos;

import javax.persistence.*;
import java.time.Instant;


@Entity
public class OrderDTO {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn
    private CustomerDTO customerID;

    @ManyToOne
    @JoinColumn
    private EmployeeDTO2 employeeID;

    private Instant shippedDate;

    private String shipAddress;

    private String shipCity;

    private String shipCountry;

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public Instant getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Instant shippedDate) {
        this.shippedDate = shippedDate;
    }

    public EmployeeDTO2 getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(EmployeeDTO2 employeeID) {
        this.employeeID = employeeID;
    }

    public CustomerDTO getCustomerID() {
        return customerID;
    }

    public void setCustomerID(CustomerDTO customerID) {
        this.customerID = customerID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}