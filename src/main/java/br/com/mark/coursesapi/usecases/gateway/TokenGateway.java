package br.com.mark.coursesapi.usecases.gateway;

import br.com.mark.coursesapi.config.jwt.AuthenticationResponse;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import org.springframework.http.ResponseEntity;

public interface TokenGateway {

    ResponseEntity<AuthenticationResponse> verifyToken(String refreshToken) throws InvalidTokenException;

}
