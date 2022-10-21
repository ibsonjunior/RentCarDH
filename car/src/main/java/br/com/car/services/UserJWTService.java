package br.com.car.services;

import br.com.car.autenticationJWTData.UserDetailsCar;
import br.com.car.dtos.UserDto;
import br.com.car.entities.User;
import br.com.car.repositories.UserRepository;
import br.com.car.services.exceptions.DatabaseCarException;
import br.com.car.services.exceptions.EntityCarNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Service
public class UserJWTService implements UserDetailsService {

    //Criptografia
    //Private final PasswordEncoder encoder;
    //Injeção do repositorio
    private final UserRepository repository;
    public UserJWTService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    //Consulta do usuario
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByLogin(username);
        //Se for vázio, lançará uma exception
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Usuário ["+ username + "] não encontrado");
        }
        return new UserDetailsCar(user);
    }

    @Transactional(readOnly = true)
    public List<UserDto> searchAll() {
        try {
            List<User> list = repository.findAll();
            return list.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
        } catch (EntityNotFoundException e) {
            throw new DatabaseCarException(
                    "Registros Não Encontrados!"
            );
        }
    }

    @Transactional(readOnly = true)
    public UserDto searchById(Integer id) {
        Optional<User> object = repository.findById(id);
        User entity = object.orElseThrow(() -> new EntityCarNotFoundException("Registro: " + id + " Não Encontrado"));
        return new UserDto(entity);
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
    public UserDto insert(UserDto dto) {
        User entity = new User();
        copyDtoForEntity(dto, entity);
        entity = repository.save(entity);
        return new UserDto(entity);
    }
    @Transactional
    public UserDto update(Integer id, UserDto dto) {
        try {
            User entity = repository.getReferenceById(id);
            copyDtoForEntity(dto, entity);
            entity = repository.save(entity);
            return new UserDto(entity);
        } catch (EntityNotFoundException e) {
            throw new DatabaseCarException(
                    "Registro" + id + "Não encontrado!"
            );
        }
    }
    private void copyDtoForEntity( UserDto dto, User entity) {
        entity.setName(dto.getName());
        entity.setLastName(dto.getLastName());
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
    }

}
