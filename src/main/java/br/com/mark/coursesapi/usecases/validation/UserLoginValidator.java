package br.com.mark.coursesapi.usecases.validation;

import br.com.mark.coursesapi.config.jwt.JwtUtils;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.AccessDeniedException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import br.com.mark.coursesapi.usecases.domain.UserDomain;
import br.com.mark.coursesapi.usecases.domain.UserOutDomain;
import br.com.mark.coursesapi.utils.BirthDateValidator;
import br.com.mark.coursesapi.utils.EmailValidator;
import br.com.mark.coursesapi.utils.PasswordValidator;
import br.com.mark.coursesapi.utils.PhoneValidator;
import org.springframework.stereotype.Component;

@Component
public class UserLoginValidator {


    public UserDomain savedUserVerify(UserDomain userDomain) throws Exception {

        EmailValidator.emailValidator(userDomain.getEmail());

        PasswordValidator.passwordValidator(userDomain.getPassword());

        PhoneValidator.phoneValidator(userDomain.getPhone());

        BirthDateValidator.birthDateValidator(userDomain.getBirthDate());

        return userDomain;

    }

    public void getByTokenValidator(String token) throws AccessDeniedException, InvalidTokenException {

        JwtUtils.isValidToken(token);

        var roleForToken = JwtUtils.getRoleFromToken(token);

        if (!roleForToken.equals("Administrador")) throw new AccessDeniedException(); // lancar execao de acesso restrito

    }

    public void savedUserOutVerify(UserOutDomain userDomain) throws Exception {

        EmailValidator.emailValidator(userDomain.getEmail());

        PasswordValidator.passwordValidator(userDomain.getPassword());

    }

    public static void getAllValidator(String token) throws AccessDeniedException {
        if (!token.equalsIgnoreCase("ADMINISTRADOR")) throw new AccessDeniedException();
    }


}