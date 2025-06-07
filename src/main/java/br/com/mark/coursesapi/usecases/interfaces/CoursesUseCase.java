package br.com.mark.coursesapi.usecases.interfaces;

import br.com.mark.coursesapi.entrypoint.handler.exceptions.AccessDeniedException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.CoursesCreationException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.UserNotFoundException;
import br.com.mark.coursesapi.usecases.domain.CoursesDomain;
import org.springframework.http.ResponseEntity;

import java.io.IOException;


public interface CoursesUseCase {

    ResponseEntity<?> saveCourses(CoursesDomain coursesDomain, String token) throws UserNotFoundException,
            AccessDeniedException,
            IOException,
            InvalidTokenException,
            CoursesCreationException;

}