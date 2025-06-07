package br.com.mark.coursesapi.usecases.validation;

import br.com.mark.coursesapi.entrypoint.handler.exceptions.CoursesCreationException;
import br.com.mark.coursesapi.usecases.domain.CoursesDomain;
import br.com.mark.coursesapi.usecases.domain.enums.DifficultyLevel;
import br.com.mark.coursesapi.usecases.domain.enums.TypeCourses;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class CoursesAddValidator {

    public void saveCoursesValidator(CoursesDomain coursesDomain) throws CoursesCreationException {

        Set<TypeCourses> listCourses = new HashSet<>(Arrays.asList(TypeCourses.DESIGN_MULTIMEDIA,
                TypeCourses.BUSINESS_ENTREPRENEURSHIP,
                TypeCourses.PROGRAMMING_DEVELOPMENT,
                TypeCourses.MARKETING_SALES));

        Set<DifficultyLevel> difficultyLevels = new HashSet<>(Arrays.asList(DifficultyLevel.INTERMEDIATE, DifficultyLevel.ADVANCED, DifficultyLevel.BEGINNER));

        if (!listCourses.contains(coursesDomain.getTypeCourses())) throw new CoursesCreationException();

        if (!difficultyLevels.contains(coursesDomain.getLevel())) throw new CoursesCreationException();

    }




}