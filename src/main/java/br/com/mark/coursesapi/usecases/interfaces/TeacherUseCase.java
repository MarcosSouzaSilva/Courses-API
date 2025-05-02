package br.com.mark.coursesapi.usecases.interfaces;

import br.com.mark.coursesapi.usecases.domain.TeacherDomain;
import org.springframework.http.ResponseEntity;


public interface TeacherUseCase {

    ResponseEntity<?> saveTeacher(TeacherDomain teacherDomain) throws Exception;

}