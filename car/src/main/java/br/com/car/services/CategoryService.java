package br.com.car.services;

import br.com.car.dtos.CarDto;
import br.com.car.dtos.CategoryDto;
import br.com.car.entities.Car;
import br.com.car.entities.Category;
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
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CarRepository carRepository;

    @Transactional(readOnly = true)
    public List<CategoryDto> searchAll() {
        try {
            List<Category> list = repository.findAll();
            return list.stream().map(x -> new CategoryDto(x)).collect(Collectors.toList());
        } catch (EntityNotFoundException e) {
            throw new DatabaseCarException(
                    "Registros Não Encontrados!"
            );
        }
    }

    @Transactional(readOnly = true)
    public CategoryDto searchById(Integer id) {
        Optional<Category> object = repository.findById(id);
        Category entity = object.orElseThrow(() -> new EntityCarNotFoundException("Registro: " + id + " Não Encontrado"));
        return new CategoryDto(entity);
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
    public CategoryDto insert(CategoryDto dto) {
        Category entity = new Category();
        copyDtoForEntity(dto, entity);
        entity = repository.save(entity);
        return new CategoryDto(entity);
    }

    @Transactional
    public CategoryDto update(Integer id, CategoryDto dto) {
        try {
            Category entity = repository.getReferenceById(id);
            copyDtoForEntity(dto, entity);
            entity = repository.save(entity);
            return new CategoryDto(entity);
        } catch (EntityNotFoundException e) {
            throw new DatabaseCarException(
                    "Registro" + id + "Não encontrado!"
            );
        }
    }

    private void copyDtoForEntity(CategoryDto dto, Category entity) {
        entity.setQualification(dto.getQualification());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());

        entity.getCar().clear();
        for (CarDto endDto : dto.getCar()) {
            Car car = carRepository.getReferenceById(endDto.getId());
            entity.getCar().add(car);
        }

    }

}