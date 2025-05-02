package br.com.mark.coursesapi.utils;

import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidEmailException;

import java.util.regex.Pattern;

public class EmailValidator {


    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static void emailValidator(String email) throws Exception {

        if (!Pattern.matches(EMAIL_REGEX, email)) throw new InvalidEmailException();

    }

}
