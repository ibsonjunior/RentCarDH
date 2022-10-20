package br.com.car.controllers.exceptions;

import br.com.car.services.exceptions.DatabaseCarException;
import br.com.car.services.exceptions.EntityCarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ManipulatorException {
    @ExceptionHandler(EntityCarNotFoundException.class)
    public ResponseEntity<ErrorCar> entityNotFound(EntityCarNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorCar error = new ErrorCar();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Registro Não Encontrado!");
        error.setMsg(e.getMessage());
        error.setLink(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseCarException.class)
    public ResponseEntity<ErrorCar> integrityViolation(DatabaseCarException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorCar error = new ErrorCar();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Violação de Integridade (Chave Estrangeira)!");
        error.setMsg(e.getMessage());
        error.setLink(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

}
