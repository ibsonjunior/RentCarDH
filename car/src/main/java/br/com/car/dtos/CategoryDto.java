package br.com.car.dtos;

import br.com.car.entities.Category;

import java.io.Serializable;

public class CategoryDto implements Serializable {
    public static final long serialVersionUID = 1L;

    private Integer id;
    private String qualification;
    private String description;
    private String image;


    public CategoryDto() {
    }

    public CategoryDto(String qualification, String description, String image) {
        this.qualification = qualification;
        this.description = description;
        this.image = image;
    }

    public CategoryDto(Category category) {
        id = category.getId();
        qualification = category.getQualification();
        description = category.getDescription();
        image = category.getImage();
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
}
