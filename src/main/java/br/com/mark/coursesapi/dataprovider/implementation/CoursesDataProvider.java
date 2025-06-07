package br.com.mark.coursesapi.dataprovider.implementation;

import br.com.mark.coursesapi.config.jwt.JwtUtils;
import br.com.mark.coursesapi.dataprovider.entity.Courses;
import br.com.mark.coursesapi.dataprovider.entity.Teachers;
import br.com.mark.coursesapi.dataprovider.mapper.CoursesDataProviderMapper;
import br.com.mark.coursesapi.dataprovider.repository.CoursesRepository;
import br.com.mark.coursesapi.dataprovider.repository.TeachersRepository;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.AccessDeniedException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.UserNotFoundException;
import br.com.mark.coursesapi.usecases.domain.CoursesDomain;
import br.com.mark.coursesapi.usecases.gateway.CoursesGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class CoursesDataProvider implements CoursesGateway {

    private final CoursesRepository coursesRepository;

    private final TeachersRepository teachersRepository;


    @Override
    public Courses save(CoursesDomain coursesDomain, String token) throws AccessDeniedException, UserNotFoundException, IOException, InvalidTokenException {

        var role = JwtUtils.getRoleFromToken(token);

        if (role.equalsIgnoreCase("student")) throw new AccessDeniedException();

        var id = JwtUtils.getIdFromBearerToken(token);

        Teachers teachers = teachersRepository.findById(id).orElseThrow(UserNotFoundException::new);

        var entity = CoursesDataProviderMapper.convert(coursesDomain);

        entity.setTeachers(teachers);
        entity.setTypeCourses(coursesDomain.getTypeCourses());

        return coursesRepository.save(entity);
    }
}
























