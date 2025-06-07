package br.com.mark.coursesapi.usecases.gateway;

import br.com.mark.coursesapi.dataprovider.entity.User;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.AccessDeniedException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.DuplicateContactException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidCredentialsException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.UserNotFoundException;
import br.com.mark.coursesapi.entrypoint.model.request.UserOutModelRequest;
import br.com.mark.coursesapi.usecases.domain.UserDomain;
import br.com.mark.coursesapi.usecases.domain.UserOutDomain;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public interface UserGateway {

    User save(UserDomain userDomain) throws DuplicateContactException;

    Long getById(Long id) throws InvalidCredentialsException, UserNotFoundException;

    Page<User> getAll(int page, int size, String token) throws InvalidCredentialsException, InvalidTokenException, AccessDeniedException;

    User saveOut(UserOutDomain user) throws InvalidCredentialsException;

    User refreshToken(Long id) throws Exception;

}