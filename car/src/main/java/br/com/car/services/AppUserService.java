package br.com.car.services;

import br.com.car.repositories.UserCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final UserCarRepository userCarRepository;
    @Autowired
    public AppUserService(UserCarRepository userCarRepository) {
        this.userCarRepository = userCarRepository;
    }

    @Override
    //Método que faz a busca no Banco de Dados
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userCarRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not Found"));
    }
}
