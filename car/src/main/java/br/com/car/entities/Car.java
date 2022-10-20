package br.com.car.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Car implements Serializable {
    public static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String location;
    @Column(columnDefinition = "TEXT")
    private String img;
    @Column(columnDefinition = "TEXT")
    private String description;
    private double price;
    //Tudo o que fizer em um reflete em no outro
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "catedory_id")
    //Tratamento de exceções
    @JsonIgnore
    private Category category;

    public Car() {
    }

    public Car(Integer id, String title, String location, String img, String description, double price, Category category) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.img = img;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Car(String title, String location, String img, String description, double price, Category category) {
        this.title = title;
        this.location = location;
        this.img = img;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
