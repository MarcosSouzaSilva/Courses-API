package br.com.mark.coursesapi.dataprovider.entity;

import br.com.mark.coursesapi.usecases.domain.enums.Role;
import br.com.mark.coursesapi.usecases.domain.enums.TypeCourses;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Teachers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String email;

    private String password;

    private LocalDate birthDate;

    private LocalDateTime createAt;

    private LocalDateTime updatedAt;

    private String phone;

    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private TypeCourses courses;




}
