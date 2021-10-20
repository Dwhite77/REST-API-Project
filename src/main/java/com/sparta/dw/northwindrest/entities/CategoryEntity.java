package com.sparta.dw.northwindrest.entities;

import javax.persistence.*;

@Table(name = "categories")
@Entity
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}