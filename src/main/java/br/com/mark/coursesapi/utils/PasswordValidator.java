package br.com.mark.coursesapi.utils;

import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidPasswordException;

import java.util.regex.Pattern;

public class PasswordValidator {


    private static final String SENHA_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    public static void passwordValidator(String password) throws Exception {

        if (!Pattern.matches(SENHA_REGEX, password)) throw new InvalidPasswordException();

    }

}
