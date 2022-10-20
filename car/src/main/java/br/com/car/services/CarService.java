package br.com.car.services;

import br.com.car.dtos.CarDto;
import br.com.car.entities.Car;
import br.com.car.repositories.CarRepository;
import br.com.car.repositories.CategoryRepository;
import br.com.car.services.exceptions.DatabaseCarException;
import br.com.car.services.exceptions.EntityCarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {
    @Autowired
    private CarRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;



    @Transactional(readOnly = true)
    public List<CarDto> searchAll() {
        try {
            List<Car> list = repository.findAll();
            return list.stream().map(x -> new CarDto(x)).collect(Collectors.toList());
        } catch (EntityNotFoundException e) {
            throw new DatabaseCarException(
                    "Registros Não Encontrados!"
            );
        }
    }

    @Transactional(readOnly = true)
    public CarDto searchById(Integer id) {
        Optional<Car> object = repository.findById(id);
        Car entity = object.orElseThrow(() -> new EntityCarNotFoundException("Registro: " + id + " Não Encontrado"));
        return new CarDto(entity);
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityCarNotFoundException(
                    "Exclusão não possível não realizada!" +
                            id + "Não encontrado!"

            );
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseCarException(
                    "Violação de integridade: Registro" +
                            id + "está em outro registro!"
            );
        }
    }

    @Transactional
    public CarDto insert(CarDto dto) {
        Car entity = new Car();
        copyDtoForEntity(dto, entity);
        entity = repository.save(entity);
        return new CarDto(entity);
    }

    @Transactional
    public CarDto update(Integer id, CarDto dto) {
        try {
            Car entity = repository.getReferenceById(id);
            copyDtoForEntity(dto, entity);
            entity = repository.save(entity);
            return new CarDto(entity);
        } catch (EntityNotFoundException e) {
            throw new DatabaseCarException(
                    "Registro" + id + "Não encontrado!"
            );
        }
    }

    private void copyDtoForEntity( CarDto dto, Car entity) {
      entity.setTitle(dto.getTitle());
      entity.setLocation(dto.getLocation());
      entity.setImg(dto.getImg());
      entity.setDescription(dto.getDescription());
      entity.setPrice(dto.getPrice());
      entity.setCategory(dto.getCategory());
    }


}
