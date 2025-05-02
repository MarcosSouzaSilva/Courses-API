package br.com.mark.coursesapi.usecases.service;

import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import br.com.mark.coursesapi.dataprovider.repository.TeachersRepository;
import br.com.mark.coursesapi.dataprovider.repository.UsersRepository;
import br.com.mark.coursesapi.config.jwt.JwtUtils;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenVerifyService {

    private final TeachersRepository teachersRepository;

    private final UsersRepository usersRepository;

    public RefreshTokenVerifyService(TeachersRepository teachersRepository, UsersRepository usersRepository) {
        this.teachersRepository = teachersRepository;
        this.usersRepository = usersRepository;
    }


    public void verifyToken(String refreshToken) throws InvalidTokenException {

        var emailForToken = JwtUtils.getEmailForToken(refreshToken);

        if (usersRepository.findByEmail(emailForToken).isEmpty() || teachersRepository.findByEmail(emailForToken).isEmpty())
            throw new InvalidTokenException();

    }

}
