package br.com.car.users;

import br.com.car.entities.AppUser;
import br.com.car.entities.AppUserRoles;
import br.com.car.repositories.UserCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
//Criação de alguns usuarios
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserCarRepository userCarRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //Criptografia da senha do user
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("goCarAdmin");
        String passwordUser = passwordEncoder.encode("tesla");

        userCarRepository.save(new AppUser("goCar", "goCarAdmin", "goCar@cars.com.br", password, AppUserRoles.ROLE_ADMIN));
        userCarRepository.save(new AppUser("Elon", "ElonMusk", "ElonMusk@tesla.com", passwordUser, AppUserRoles.ROLE_USER));
    }
}
