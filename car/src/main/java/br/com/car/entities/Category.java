package br.com.car.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Category implements Serializable {
    public static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String qualification;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String image;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Car> car = new HashSet<>();

    public Category() {}

    public Category(Integer id, String qualification, String description, String image, Set<Car> car) {
        this.id = id;
        this.qualification = qualification;
        this.description = description;
        this.image = image;
        this.car = car;
    }

    public Category(String qualification, String description, String image, Set<Car> car) {
        this.qualification = qualification;
        this.description = description;
        this.image = image;
        this.car = car;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Car> getCar() {
        return car;
    }

    public void setCar(Set<Car> car) {
        this.car = car;
    }
}
