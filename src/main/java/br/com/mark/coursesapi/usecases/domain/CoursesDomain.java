package br.com.mark.coursesapi.usecases.domain;

import br.com.mark.coursesapi.usecases.domain.enums.DifficultyLevel;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CoursesDomain {



    String title;

    String description;

    BigDecimal price;

    String thumbnailUrl;

    DifficultyLevel level; // BEGINNER, INTERMEDIATE, ADVANCED

    CategoryDomain categoryDomain;

    Boolean published;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    @ManyToOne
    private List<UserDomain> userDomain;

    @OneToMany
    private ModuleDomain moduleDomain;

    @OneToMany
    private EnrollmentDomain enrollmentDomain;

    @OneToMany
    private ReviewDomain reviewDomain;




}