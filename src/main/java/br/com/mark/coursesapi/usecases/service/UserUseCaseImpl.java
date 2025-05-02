package br.com.mark.coursesapi.usecases.service;


import br.com.mark.coursesapi.dataprovider.entity.User;
import br.com.mark.coursesapi.dataprovider.implementation.UserDataProvider;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.AccessDeniedException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidCredentialsException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import br.com.mark.coursesapi.usecases.domain.UserDomain;
import br.com.mark.coursesapi.config.jwt.AuthenticationResponse;
import br.com.mark.coursesapi.config.jwt.JwtUtils;
import br.com.mark.coursesapi.usecases.domain.UserOutDomain;
import br.com.mark.coursesapi.usecases.gateway.UserGateway;
import br.com.mark.coursesapi.usecases.interfaces.UserUseCase;
import br.com.mark.coursesapi.usecases.validation.UserLoginValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserUseCaseImpl implements UserUseCase {


    private final UserDataProvider userDataProvider;

    private UserGateway userGateway;

    private final UserLoginValidator useCaseUser;

    private final PasswordEncoder passwordEncoder;


    @Override
    public ResponseEntity<?> saveUser(UserDomain userDomain) throws Exception {

        var validator = useCaseUser.savedUserVerify(userDomain);

        validator.setPassword(passwordEncoder.encode(validator.getPassword()));

        var userEntity = userGateway.save(validator);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse
                (JwtUtils.generateTokenForUser(userEntity), JwtUtils.generateRefreshTokenForUser(userEntity), null);

        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);
    }


    @Override
    public ResponseEntity<?> getById(Long id, String token) throws InvalidTokenException, InvalidCredentialsException, AccessDeniedException {

        JwtUtils.validateToken(token);

        var idToken = userGateway.getById(JwtUtils.getIdForToken(token));

        if (!Objects.equals(id, idToken.get().getId())) throw new InvalidCredentialsException();

        useCaseUser.getByTokenValidator(token);

        return ResponseEntity.status(HttpStatus.OK).body(userGateway.getById(idToken.get().getId()));

    }

    public ResponseEntity<Page<User>> getAll(int page, int size, String token) throws InvalidTokenException, AccessDeniedException {
        if (token != null && token.startsWith("Bearer ")) {
            String tokenValid = token.substring(7);
            return ResponseEntity.status(HttpStatus.OK).body(userDataProvider.getAll(page, size, tokenValid));
        } else {
            throw new InvalidTokenException();
        }
    }

    public ResponseEntity<?> signOutUser(UserOutDomain domain) throws Exception {

        useCaseUser.savedUserOutVerify(domain);

        var entity = userGateway.saveOut(domain);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(JwtUtils.generateTokenForUserOut(entity, entity.getId()), JwtUtils.generateRefreshTokenForUserOut(entity, entity.getId()), null);

        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);
    }

}