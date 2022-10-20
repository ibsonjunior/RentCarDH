package br.com.car.repositories;

import br.com.car.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //Busca o login
    public Optional<User> findByLogin(String login);
}
