package br.com.mark.coursesapi.unittests;

import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidEmailException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidPasswordException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidPhoneNumberException;
import br.com.mark.coursesapi.entrypoint.mapper.request.UserEntryPointRequestMapper;
import br.com.mark.coursesapi.entrypoint.model.request.UserModelRequest;
import br.com.mark.coursesapi.usecases.domain.UserDomain;
import br.com.mark.coursesapi.usecases.domain.enums.DifficultyLevel;
import br.com.mark.coursesapi.usecases.domain.enums.PaymentStatus;
import br.com.mark.coursesapi.usecases.domain.enums.Role;
import br.com.mark.coursesapi.usecases.domain.enums.TypeCourses;
import br.com.mark.coursesapi.usecases.validation.UserLoginValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class UserEntityTest {

    private final UserLoginValidator userLoginValidator = new UserLoginValidator();

    @Test
    @DisplayName("Deve lançar exceção para senha inválida")
    public void shouldThrowExceptionWhenPasswordIsInvalid() {
        UserModelRequest userModelRequest = createUserWithInvalidPassword();
        UserDomain domain = UserEntryPointRequestMapper.convert(userModelRequest);

        assertThrows(InvalidPasswordException.class, () -> {
            userLoginValidator.savedUserVerify(domain);
        });
    }


    @Test
    @DisplayName("Deve lançar exceção para e-mail inválido")
    public void shouldThrowExceptionWhenEmailIsInvalid() {
        UserModelRequest userModelRequest = createUserWithInvalidEmail();
        UserDomain domain = UserEntryPointRequestMapper.convert(userModelRequest);

        assertThrows(InvalidEmailException.class, () -> {
            userLoginValidator.savedUserVerify(domain);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção para telefone inválido")
    public void shouldThrowExceptionWhenPhoneIsInvalid() {
        UserModelRequest userModelRequest = createUserWithInvalidPhone();
        UserDomain domain = UserEntryPointRequestMapper.convert(userModelRequest);

        assertThrows(InvalidPhoneNumberException.class, () -> {
            userLoginValidator.savedUserVerify(domain);
        });
    }

    @Test
    @DisplayName("Verifica valores dos enums de categorias de cursos")
    public void shouldVerifyCourseTypeEnumValues() {
        assertEquals("Marketing & Sales", TypeCourses.MARKETING_SALES.getCourse());
        assertEquals("Programming & Development", TypeCourses.PROGRAMMING_DEVELOPMENT.getCourse());
        assertEquals("Technology & Data", TypeCourses.TECHNOLOGY_DATA.getCourse());
        assertEquals("Design & Multimedia", TypeCourses.DESIGN_MULTIMEDIA.getCourse());
        assertEquals("Business & Entrepreneurship", TypeCourses.BUSINESS_ENTREPRENEURSHIP.getCourse());
    }

    @Test
    @DisplayName("Verifica valores dos enums de papel do usuário")
    public void shouldVerifyRoleEnumValues() {
        assertEquals("Administrador", Role.ADMIN.getRole());
        assertEquals("Instrutor", Role.INSTRUCTOR.getRole());
        assertEquals("Estudante", Role.STUDENT.getRole());
    }

    @Test
    @DisplayName("Verifica valores dos enums de nível de dificuldade")
    public void shouldVerifyDifficultyLevelEnumValues() {
        assertEquals("Avançado", DifficultyLevel.ADVANCED.getLevel());
        assertEquals("Intermediário", DifficultyLevel.INTERMEDIATE.getLevel());
        assertEquals("Iniciante", DifficultyLevel.BEGINNER.getLevel());
    }

    @Test
    @DisplayName("Verifica valores dos enums de status de pagamento")
    public void shouldVerifyPaymentStatusEnumValues() {
        assertEquals("Pendente", PaymentStatus.PENDING.getLabel());
        assertEquals("Falhou", PaymentStatus.FAILED.getLabel());
        assertEquals("Confirmado", PaymentStatus.CONFIRMED.getLabel());
    }


    public static UserModelRequest createUser() {
        return UserModelRequest.builder().email("email@gmail.com").fullName("Mark").phone("11985473788").password("Silva12@").birthDate(LocalDate.now()).build();
    }

    public static UserModelRequest createUserWithInvalidPassword() {
        return UserModelRequest.builder().email("email@gmail.com").fullName("Mark").phone("11957492844").password("s").birthDate(LocalDate.now()).build();
    }

    public static UserModelRequest createUserWithInvalidEmail() {
        return UserModelRequest.builder().email("emailgmail.com").fullName("Mark").phone("11957492844").password("Silva12@").birthDate(LocalDate.now()).build();
    }

    public static UserModelRequest createUserWithInvalidPhone() {
        return UserModelRequest.builder().email("email@gmail.com").fullName("Mark").phone("1").password("Silva12@").birthDate(LocalDate.now()).build();
    }

}