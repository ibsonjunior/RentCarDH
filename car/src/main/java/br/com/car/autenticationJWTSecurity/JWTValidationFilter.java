package br.com.car.autenticationJWTSecurity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//Autenticação e validação da autenticação
public class JWTValidationFilter extends BasicAuthenticationFilter {
    //Atributo do cabeçalho
    public static final String HEADER_ATRIBUTE = "Authorization";
    //Bearer
    public static final String ATRIBUTE_PREFIXO = "Bearer";

    public JWTValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {


        String atribute = request.getHeader(HEADER_ATRIBUTE);

        if (atribute == null){
            chain.doFilter(request, response);
            return;
        }

       if (!atribute.startsWith(ATRIBUTE_PREFIXO)){
           chain.doFilter(request, response);
           return;
       }

       String token = atribute.replace(ATRIBUTE_PREFIXO, "");
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token){
        String user = JWT.require(Algorithm.HMAC512(JWTAtutenticationFilter.TOKEN_PASSWORD))
                .build()
                .verify(token)
                .getSubject();

        if (user == null){
            return null;
        }

        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
    }
}
