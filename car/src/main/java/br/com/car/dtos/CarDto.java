package br.com.car.dtos;

import br.com.car.entities.Car;
import br.com.car.entities.Category;

import java.io.Serializable;

public class CarDto implements Serializable {
    public static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private String location;
    private String img;
    private String description;
    private double price;
    private Category category;



    public CarDto() {
    }

    public CarDto(Car car) {
       id = car.getId();
        title = car.getTitle();
        location = car.getLocation();
        img = car.getImg();
        description = car.getDescription();
        price = car.getPrice();
        category = car.getCategory();
    }

    public CarDto(String title, String location, String img, String description, double price) {
        this.title = title;
        this.location = location;
        this.img = img;
        this.description = description;
        this.price = price;
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
