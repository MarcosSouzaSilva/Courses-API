package br.com.mark.coursesapi.usecases.service;

import br.com.mark.coursesapi.entrypoint.handler.exceptions.AccessDeniedException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.CoursesCreationException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.UserNotFoundException;
import br.com.mark.coursesapi.usecases.domain.CoursesDomain;
import br.com.mark.coursesapi.usecases.gateway.CoursesGateway;
import br.com.mark.coursesapi.usecases.interfaces.CoursesUseCase;
import br.com.mark.coursesapi.usecases.validation.CoursesAddValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@AllArgsConstructor
public class CoursesUseCaseImpl implements CoursesUseCase {

    private final CoursesGateway coursesGateway;

    private final CoursesAddValidator coursesAddValidator;

    @Override
    public ResponseEntity<?> saveCourses(CoursesDomain coursesDomain, String token) throws
            UserNotFoundException,
            AccessDeniedException,
            IOException,
            CoursesCreationException,
            InvalidTokenException {

        coursesAddValidator.saveCoursesValidator(coursesDomain);

        coursesGateway.save(coursesDomain, token);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}