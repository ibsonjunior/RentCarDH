package br.com.car.dtos;

import br.com.car.entities.Car;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CarDto implements Serializable {
    public static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private String location;
    private String img;
    private String description;
    private double price;
    private Set<CategoryDto> categories = new HashSet<>();



    public CarDto() {
    }

    public CarDto(Car car) {
       id = car.getId();
        title = car.getTitle();
        location = car.getLocation();
        img = car.getImg();
        description = car.getDescription();
        price = car.getPrice();
        car.getCategories().forEach(categories -> this.categories.add(new CategoryDto(categories)));
    }

    public CarDto(String title, String location, String img, String description, double price, Set<CategoryDto> categories) {
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

    public Set<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDto> categories) {
        this.categories = categories;
    }
}
