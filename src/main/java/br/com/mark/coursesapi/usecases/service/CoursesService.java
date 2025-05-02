package br.com.mark.coursesapi.usecases.service;

import br.com.mark.coursesapi.usecases.domain.CoursesDomain;
import br.com.mark.coursesapi.dataprovider.repository.CoursesRepository;
import br.com.mark.coursesapi.usecases.validation.CoursesAddValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoursesService {


    private final CoursesAddValidator coursesAddValidator;

    private final CoursesRepository repository;



    public void addCourses (CoursesDomain coursesDomain){



    }
}
