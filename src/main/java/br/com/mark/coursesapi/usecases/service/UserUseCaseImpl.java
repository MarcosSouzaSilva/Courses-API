package br.com.mark.coursesapi.usecases.service;

import br.com.mark.coursesapi.dataprovider.entity.User;
import br.com.mark.coursesapi.dataprovider.implementation.UserDataProvider;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.AccessDeniedException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidCredentialsException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.UserNotFoundException;
import br.com.mark.coursesapi.usecases.domain.UserDomain;
import br.com.mark.coursesapi.config.jwt.AuthenticationResponse;
import br.com.mark.coursesapi.config.jwt.JwtUtils;
import br.com.mark.coursesapi.usecases.domain.UserOutDomain;
import br.com.mark.coursesapi.usecases.gateway.UserGateway;
import br.com.mark.coursesapi.usecases.interfaces.EmailUseCase;
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

    private final UserGateway userGateway;

    private final UserLoginValidator useCaseUser;

    private final PasswordEncoder passwordEncoder;

    private final EmailUseCase emailSender;

    @Override
    public ResponseEntity<AuthenticationResponse> saveUser(UserDomain userDomain) throws Exception {

        var validator = useCaseUser.savedUserVerify(userDomain);

        validator.setPassword(passwordEncoder.encode(validator.getPassword()));

        var userEntity = userGateway.save(validator);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(JwtUtils.generateAccessTokenForUser(userEntity), JwtUtils.generateRefreshTokenForUser(userEntity));

        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);
    }

    @Override
    public ResponseEntity<Long> getById(Long id, String token) throws InvalidCredentialsException, InvalidTokenException, UserNotFoundException, AccessDeniedException {

        Long idToken = userGateway.getById(JwtUtils.getIdFromBearerToken(token));

        if (!Objects.equals(id, idToken)) throw new InvalidCredentialsException();

        useCaseUser.getByTokenValidator(token);

        return ResponseEntity.status(HttpStatus.OK).body(userGateway.getById(idToken));
    }

    @Override
    public ResponseEntity<Page<User>> getAll(int page, int size, String token) throws InvalidTokenException, AccessDeniedException {
        return ResponseEntity.status(HttpStatus.OK).body(userDataProvider.getAll(page, size, token));
    }

    @Override
    public ResponseEntity<?> signOutUser(UserOutDomain domain) throws Exception {

        useCaseUser.savedUserOutVerify(domain);

        var entity = userGateway.saveOut(domain);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(JwtUtils.generateExternalAccessTokenForUser(entity, entity.getId()), JwtUtils.generateExternalRefreshTokenForUser(entity, entity.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);
    }

    @Override
    public ResponseEntity<?> refreshToken(String token) throws Exception {

        var id = JwtUtils.getIdFromToken(token);

        var userVerify = userDataProvider.refreshToken(id);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(JwtUtils.generateExternalRefreshTokenForUser(userVerify, userVerify.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);
    }


}