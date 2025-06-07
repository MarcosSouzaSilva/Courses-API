package br.com.mark.coursesapi.unittests;

import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidEmailException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidPasswordException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidPhoneNumberException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidYearException;
import br.com.mark.coursesapi.entrypoint.mapper.request.TeacherEntryPointRequestMapper;
import br.com.mark.coursesapi.entrypoint.model.request.TeacherModelRequest;
import br.com.mark.coursesapi.usecases.domain.TeacherDomain;
import br.com.mark.coursesapi.usecases.domain.enums.TypeCourses;
import br.com.mark.coursesapi.usecases.validation.TeacherLoginValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TeacherEntityTest {

    private final TeacherLoginValidator teacherLoginValidator = new TeacherLoginValidator();


    @Test
    @DisplayName("Deve lançar exceção para senha inválida")
    public void shouldThrowExceptionWhenPasswordIsInvalid() {
        TeacherModelRequest userModelRequest = createTeacherWithInvalidPassword();
        TeacherDomain domain = TeacherEntryPointRequestMapper.convert(userModelRequest);

        assertThrows(InvalidPasswordException.class, () -> {
            teacherLoginValidator.teacherValidator(domain);
        });
    }


    @Test
    @DisplayName("Deve lançar exceção para e-mail inválido")
    public void shouldThrowExceptionWhenEmailIsInvalid() {
        TeacherModelRequest userModelRequest = createTeacherWithInvalidEmail();
        TeacherDomain domain = TeacherEntryPointRequestMapper.convert(userModelRequest);

        assertThrows(InvalidEmailException.class, () -> {
            teacherLoginValidator.teacherValidator(domain);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção para telefone inválido")
    public void shouldThrowExceptionWhenPhoneIsInvalid() {
        TeacherModelRequest userModelRequest = createTeacherWithInvalidPhone();
        TeacherDomain domain = TeacherEntryPointRequestMapper.convert(userModelRequest);

        assertThrows(InvalidPhoneNumberException.class, () -> {
            teacherLoginValidator.teacherValidator(domain);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção para aniversario inválido")
    public void shouldThrowExceptionWhenBirthDateIsInvalid() {
        TeacherModelRequest userModelRequest = createTeacherWithInvalidBirthDate();
        TeacherDomain domain = TeacherEntryPointRequestMapper.convert(userModelRequest);

        assertThrows(InvalidYearException.class, () -> {
            teacherLoginValidator.teacherValidator(domain);
        });
    }


    public static TeacherModelRequest createTeacherWithInvalidPassword() {
        return TeacherModelRequest.builder().email("email@gmail.com").fullName("Mark").phone("11957492844").courses(TypeCourses.TECHNOLOGY_DATA).password("s").birthDate(LocalDate.now()).build();
    }

    public static TeacherModelRequest createTeacherWithInvalidEmail() {
        return TeacherModelRequest.builder().email("emailgmail.com").fullName("Mark").phone("11957492844").courses(TypeCourses.TECHNOLOGY_DATA).password("Silva12@").birthDate(LocalDate.now()).build();
    }


    public static TeacherModelRequest createTeacherWithInvalidPhone() {
        return TeacherModelRequest.builder().email("email@gmail.com").fullName("Mark").phone("1").courses(TypeCourses.TECHNOLOGY_DATA).password("Silva12@").birthDate(LocalDate.now()).build();
    }

    public static TeacherModelRequest createTeacherWithInvalidBirthDate() {
        return TeacherModelRequest.builder().email("email@gmail.com").fullName("Mark").phone("11987654321").courses(TypeCourses.TECHNOLOGY_DATA).password("Silva12@").birthDate(LocalDate.of(2026,8,22)).build();
    }

}