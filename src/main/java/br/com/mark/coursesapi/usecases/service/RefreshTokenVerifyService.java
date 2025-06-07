package br.com.mark.coursesapi.usecases.service;

import br.com.mark.coursesapi.config.jwt.AuthenticationResponse;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import br.com.mark.coursesapi.dataprovider.repository.TeachersRepository;
import br.com.mark.coursesapi.dataprovider.repository.UsersRepository;
import br.com.mark.coursesapi.config.jwt.JwtUtils;
import br.com.mark.coursesapi.usecases.gateway.TokenGateway;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RefreshTokenVerifyService implements TokenGateway {

    private final TeachersRepository teachersRepository;

    private final UsersRepository usersRepository;


    public ResponseEntity<AuthenticationResponse> verifyToken(String refreshToken) throws InvalidTokenException {

        var emailForToken = JwtUtils.getEmailFromToken(refreshToken);

        var user = usersRepository.findByEmail(emailForToken);

        var teacher = teachersRepository.findByEmail(emailForToken);

        if (user.isPresent()) {

            AuthenticationResponse authenticationResponse = new AuthenticationResponse(JwtUtils.generateAccessTokenForUser(user.get()), JwtUtils.generateRefreshTokenForUser(user.get()));
            return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);

        } else if (teacher.isPresent()) {

            AuthenticationResponse authenticationResponse = new AuthenticationResponse(JwtUtils.generateAccessTokenForTeacher(teacher.get()), JwtUtils.generateRefreshTokenForTeacher(teacher.get()));
            return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);

        } else {
            throw new InvalidTokenException();
        }
    }

}