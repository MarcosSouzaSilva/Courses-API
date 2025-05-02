package br.com.mark.coursesapi.config.jwt;

import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Se for uma rota pública, continua sem verificar o token
        if (shouldNotFilter(request)) {

            filterChain.doFilter(request, response);
            return;
        }

        // Verifica o token nas rotas protegidas
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                if (JwtUtils.validateToken(token)) {
                    filterChain.doFilter(request, response);  // Token válido, segue o fluxo
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // Token inválido
                }
            } catch (InvalidTokenException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // Sem token
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

        return path.equals("/api/v1/courses/signIn") || path.equals("/api/v1/courses/teachers");
    }


}
