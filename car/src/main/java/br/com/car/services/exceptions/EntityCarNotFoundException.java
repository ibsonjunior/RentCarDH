package br.com.car.services.exceptions;

public class EntityCarNotFoundException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public EntityCarNotFoundException(String msg){
        super(msg);
    }
}
