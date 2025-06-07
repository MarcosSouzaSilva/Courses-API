package br.com.mark.coursesapi.usecases.interfaces;

import br.com.mark.coursesapi.config.jwt.AuthenticationResponse;
import br.com.mark.coursesapi.dataprovider.entity.User;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.AccessDeniedException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidCredentialsException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.UserNotFoundException;
import br.com.mark.coursesapi.usecases.domain.UserDomain;
import br.com.mark.coursesapi.usecases.domain.UserOutDomain;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface UserUseCase {

    ResponseEntity<AuthenticationResponse> saveUser(UserDomain userDomain) throws Exception;

    ResponseEntity<?> getById(Long id, String token) throws UserNotFoundException, InvalidCredentialsException, InvalidTokenException, AccessDeniedException;

    ResponseEntity<Page<User>> getAll(int page, int size, String token) throws InvalidTokenException, AccessDeniedException;

    ResponseEntity<?> signOutUser(UserOutDomain user) throws Exception;

    ResponseEntity<?> refreshToken(String token) throws Exception;


}