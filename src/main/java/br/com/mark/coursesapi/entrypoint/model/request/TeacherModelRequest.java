package br.com.mark.coursesapi.entrypoint.model.request;

import br.com.mark.coursesapi.usecases.domain.enums.TypeCourses;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeacherModelRequest {

    @NotBlank
    private String fullName;

    @Email
    private String email;

    @NotBlank
    private String password;

    private LocalDate birthDate;

    @NotBlank
    @Size(max = 11)
    private String phone;

    private TypeCourses courses;


}