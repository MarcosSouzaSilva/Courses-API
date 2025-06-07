package br.com.mark.coursesapi.config.jwt;

import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Se for uma rota pública, continua sem verificar o token
        if (shouldNotFilter(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        StringBuffer authURL = request.getRequestURL();

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            try {
                if (isSpecialPublicUrl(authURL)) {
                    filterChain.doFilter(request, response);
                    return;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        String token = authHeader.substring(7).trim();

        // Tenta validar o token
        try {
            if (JwtUtils.isValidToken(token)) {
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value()); // Token inválido
            }
        } catch (InvalidTokenException | io.jsonwebtoken.io.DecodingException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    private boolean isSpecialPublicUrl(StringBuffer authURL) throws Exception {
        if (authURL.substring(50, 52) == null) {
            throw new Exception("");
        } else {
            return true;
        }
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

        return path.equals("/api/v1/courses/signIn") || path.equals("/api/v1/courses/teachers") || path.equals("/api/v1/courses/signOut") || path.equals("/api/v1/courses/auth/refresh/");
    }


}
