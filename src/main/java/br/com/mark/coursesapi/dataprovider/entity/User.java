package br.com.mark.coursesapi.dataprovider.entity;

import br.com.mark.coursesapi.usecases.domain.UserDomain;
import br.com.mark.coursesapi.usecases.domain.UserOutDomain;
import br.com.mark.coursesapi.usecases.domain.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String email;

    private String password;

    private LocalDate birthDate;

    private String phone;

    private Boolean enabled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany
    private List<Enrollment> enrollment;

    @OneToMany
    private List<Courses> courses;

    @OneToMany
    private List<Review> review;

    @OneToMany
    private List<Progress> progress;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String fullName, String email, String password,Role role, LocalDate birthDate, String phone, Boolean enabled, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.phone = phone;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = role;
    }










}