package br.com.mark.coursesapi.dataprovider.mapper;

import br.com.mark.coursesapi.dataprovider.entity.Teachers;
import br.com.mark.coursesapi.usecases.domain.TeacherDomain;
import br.com.mark.coursesapi.usecases.domain.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TeacherDataProviderMapper {

    public TeacherDataProviderMapper() {
    }

    public static Teachers convert(TeacherDomain userDomain) {
        return Teachers.builder()
                .fullName(userDomain.getFullName())
                .email(userDomain.getEmail())
                .password(userDomain.getPassword())
                .phone(userDomain.getPhone())
                .enabled(true)
                .birthDate(userDomain.getBirthDate() != null ? userDomain.getBirthDate() : LocalDate.now())
                .createAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .courses(userDomain.getCourses())
                .role(Role.INSTRUCTOR)
                .build();
    }

}