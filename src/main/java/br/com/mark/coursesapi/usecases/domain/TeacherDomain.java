package br.com.mark.coursesapi.usecases.domain;

import br.com.mark.coursesapi.usecases.domain.enums.TypeCourses;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Builder
public class TeacherDomain {

    private String fullName;

    private String email;

    @Setter
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String phone;

    @Enumerated(EnumType.STRING)
    private TypeCourses courses;


}