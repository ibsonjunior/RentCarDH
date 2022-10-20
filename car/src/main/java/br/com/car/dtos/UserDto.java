package br.com.car.dtos;

import br.com.car.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.io.Serializable;

public class UserDto implements Serializable {
    public static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String lastName;
    private String login;
    private String password;

    public UserDto() {
    }

    public UserDto(String name, String lastName, String login, String password) {
        this.name = name;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    public UserDto(User user) {
        name = user.getName();
        lastName = user.getLastName();
        login = user.getLogin();
        password = user.getPassword();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
