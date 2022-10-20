package br.com.car.autenticationJWTSecurity;
import br.com.car.autenticationJWTData.UserDetailsCar;
import br.com.car.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

//Autenticar o usuario e gerar token jwt
public class JWTAtutenticationFilter extends UsernamePasswordAuthenticationFilter {

//    psfi para fazer a classe abaixo
    public static final int TOKEN_EXPIRATION = 600000_000;
    //Senha de geração do token
    public static final String TOKEN_PASSWORD = "35ce8d70-2607-4054-974a-1846d639de30";

    private final AuthenticationManager authenticationManager;

    public JWTAtutenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //sobescrever metódo de autenticação
    //crtl + o
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //tratamento do login do usuário
        //Ele exige um try catch
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getLogin(),
                    user.getPassword(),
                    //Seria as permissões do usuario, mas não fizemos esse tratamento
                    new ArrayList<>()
            ));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuario", e);
        }

    }

    //Caso a autenticação dê certo
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //Os parentesis são um tratamento para um objeto, por não ser uma classe generica
        UserDetailsCar userDetails = (UserDetailsCar) authResult.getPrincipal();

        //geração do token
        //Gerar e criptografar token
        String token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));

        //Registrar no corpo da página
        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
