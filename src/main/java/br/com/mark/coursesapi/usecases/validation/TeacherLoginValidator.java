package br.com.mark.coursesapi.usecases.validation;

import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidCoursesException;
import br.com.mark.coursesapi.usecases.domain.TeacherDomain;
import br.com.mark.coursesapi.usecases.domain.enums.TypeCourses;
import br.com.mark.coursesapi.utils.BirthDateValidator;
import br.com.mark.coursesapi.utils.EmailValidator;
import br.com.mark.coursesapi.utils.PasswordValidator;
import br.com.mark.coursesapi.utils.PhoneValidator;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class TeacherLoginValidator {


    public TeacherDomain saveTeacherValidator(TeacherDomain teachersDomain) throws Exception {

        Set<TypeCourses> listCourses = new HashSet<>(Arrays.asList(TypeCourses.DESIGN_MULTIMEDIA,
                TypeCourses.BUSINESS_ENTREPRENEURSHIP,
                TypeCourses.PROGRAMMING_DEVELOPMENT,
                TypeCourses.MARKETING_SALES));

        EmailValidator.emailValidator(teachersDomain.getEmail());

        PasswordValidator.passwordValidator(teachersDomain.getPassword());

        PhoneValidator.phoneValidator(teachersDomain.getPhone());

        BirthDateValidator.birthDateValidator(teachersDomain.getBirthDate());

        if (!listCourses.contains(teachersDomain.getCourses())) throw new InvalidCoursesException();

        return teachersDomain;
    }



}