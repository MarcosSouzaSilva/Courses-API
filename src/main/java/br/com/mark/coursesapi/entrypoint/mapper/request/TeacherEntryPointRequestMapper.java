package br.com.mark.coursesapi.entrypoint.mapper.request;

import br.com.mark.coursesapi.entrypoint.model.request.TeacherModelRequest;
import br.com.mark.coursesapi.usecases.domain.TeacherDomain;

public class TeacherEntryPointRequestMapper {

    public static TeacherDomain convert(TeacherModelRequest teacherModelRequest) {

        return TeacherDomain.builder()
                .fullName(teacherModelRequest.getFullName())
                .email(teacherModelRequest.getEmail())
                .password(teacherModelRequest.getPassword())
                .birthDate(teacherModelRequest.getBirthDate())
                .phone(teacherModelRequest.getPhone())
                .courses(teacherModelRequest.getCourses())
                .build();
    }

}