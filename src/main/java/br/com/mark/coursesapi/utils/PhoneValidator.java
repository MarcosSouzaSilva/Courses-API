package br.com.mark.coursesapi.utils;

import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidPhoneNumberException;

import java.util.regex.Pattern;

public class PhoneValidator {

    private static final String TELEFONE_REGEX = "^\\(?\\d{2}\\)?[\\s-]?\\d{4,5}-?\\d{4}$";


    public static void phoneValidator(String phone) throws Exception {

        if (!Pattern.matches(TELEFONE_REGEX, phone)) throw new InvalidPhoneNumberException();

    }

}
