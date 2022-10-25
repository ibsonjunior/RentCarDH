package br.com.car.repositories;

import br.com.car.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserCarRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);
}
