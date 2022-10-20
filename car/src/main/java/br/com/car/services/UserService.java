package br.com.car.services;

import br.com.car.autenticationJWTData.UserDetailsCar;
import br.com.car.entities.User;
import br.com.car.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService implements UserDetailsService {

    //Injeção do repositorio
    private final UserRepository repository;
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    //Consulta do usuario
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByLogin(username);
        //Se for vázio, lançará uma excetion
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Usuário ["+ username + "] não encontrado");
        }


        return new UserDetailsCar(user);
    }
}
