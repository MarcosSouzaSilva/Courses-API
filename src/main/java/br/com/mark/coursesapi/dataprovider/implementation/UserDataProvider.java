package br.com.mark.coursesapi.dataprovider.implementation;

import br.com.mark.coursesapi.config.jwt.JwtUtils;
import br.com.mark.coursesapi.dataprovider.entity.User;
import br.com.mark.coursesapi.dataprovider.mapper.UserDataProviderMapper;
import br.com.mark.coursesapi.dataprovider.repository.UsersRepository;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.AccessDeniedException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.DuplicateContactException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidCredentialsException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import br.com.mark.coursesapi.usecases.domain.UserDomain;
import br.com.mark.coursesapi.usecases.domain.UserOutDomain;
import br.com.mark.coursesapi.usecases.gateway.UserGateway;
import br.com.mark.coursesapi.usecases.validation.UserLoginValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserDataProvider implements UserGateway {

    private final UsersRepository usersRepository;

    @Override
    public User save(UserDomain userDomain) throws DuplicateContactException {

        var userToEntity = UserDataProviderMapper.convert(userDomain);

        if (usersRepository.findByEmail(userToEntity.getEmail()).isPresent() || usersRepository.findByPhone(userToEntity.getPhone()).isPresent())
            throw new DuplicateContactException();


        return usersRepository.save(userToEntity);
    }

    @Override
    public Optional<User> getById(Long id) throws InvalidCredentialsException {

        Optional<User> user;

        if (usersRepository.findById(id).isEmpty()) throw new InvalidCredentialsException();

        user = usersRepository.findById(id);

        return user;
    }

    @Override
    public Page<User> getAll(int page, int size, String token) throws InvalidTokenException, AccessDeniedException {

        Pageable pageable = PageRequest.of(page, size);

        String verifyIfUserIsADMIN = JwtUtils.getRoleForToken(token);

        JwtUtils.validateToken(token);

        UserLoginValidator.getAllValidator(verifyIfUserIsADMIN);

        return usersRepository.findAll(pageable);
    }

    @Override
    public User saveOut(UserOutDomain domain) throws InvalidCredentialsException {

        var findUser = usersRepository.findByEmail(domain.getEmail());

        if (findUser.isEmpty()) throw new InvalidCredentialsException();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!passwordEncoder.matches(domain.getPassword(), findUser.get().getPassword())) throw new InvalidCredentialsException();

        return findUser.get();
    }


}
























