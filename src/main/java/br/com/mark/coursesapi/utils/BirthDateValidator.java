package br.com.mark.coursesapi.utils;

import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidYearException;

import java.time.LocalDate;

public class BirthDateValidator {


    public static void birthDateValidator(LocalDate data) throws InvalidYearException {
        String yearType = data.toString().substring(0,4);
        Integer yearTypeConvertToInteger = Integer.parseInt(yearType);

        if (yearTypeConvertToInteger > LocalDate.now().getYear() || data.isAfter(LocalDate.now())) throw new InvalidYearException();

    }

}
