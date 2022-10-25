package br.com.car.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    @ManyToMany
    @JoinTable(name = "car_category",
            joinColumns = @JoinColumn(name = "id_car"),
            inverseJoinColumns = @JoinColumn(name = "id_category"))
    @JsonIgnore
    Set<Category> categories = new HashSet<>();

    public Car() {
    }

    public Car(Integer id, String title, String location, String img, String description, double price, Set<Category> categories) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.img = img;
        this.description = description;
        this.price = price;
        this.categories = categories;
    }

    public Car(String title, String location, String img, String description, double price, Set<Category> categories) {
        this.title = title;
        this.location = location;
        this.img = img;
        this.description = description;
        this.price = price;
        this.categories = categories;
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
