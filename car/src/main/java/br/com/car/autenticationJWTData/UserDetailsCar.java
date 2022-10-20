package br.com.car.autenticationJWTData;

import br.com.car.entities.User;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


public class UserDetailsCar implements UserDetails {

    private final Optional<User> user;

    public UserDetailsCar(Optional<User> user) {
        this.user = user;
    }


    @Override
//    Permissões dos usuarios
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        //Se for vázio retorna ele mesmo que seja vázio/usuario com senha vázia
        return user.orElse(new User()).getPassword();
    }

    @Override
    public String getUsername() {
        return user.orElse(new User()).getEmail();
    }
    // Usuario expirado
    // true em todos eles válida o usuario
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //Usuario bloqueado
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //credenciais expiradas
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //usuario ativo
    @Override
    public boolean isEnabled() {
        return true;
    }
}
