package br.com.mark.coursesapi.usecases.gateway;

import br.com.mark.coursesapi.dataprovider.entity.Courses;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.AccessDeniedException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.UserNotFoundException;
import br.com.mark.coursesapi.usecases.domain.CoursesDomain;

import java.io.IOException;

public interface CoursesGateway {

    Courses save (CoursesDomain coursesDomain, String token) throws AccessDeniedException, UserNotFoundException, IOException, InvalidTokenException;

}