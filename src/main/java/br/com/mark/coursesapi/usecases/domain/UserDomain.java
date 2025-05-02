package br.com.mark.coursesapi.usecases.domain;


import br.com.mark.coursesapi.usecases.domain.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class UserDomain {


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





}