package br.com.mark.coursesapi.dataprovider.entity;

import br.com.mark.coursesapi.usecases.domain.enums.DifficultyLevel;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String video;

    private String image;

    private BigDecimal price;

    private String thumbnailUrl;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel level; // BEGINNER, INTERMEDIATE, ADVANCED

/*
    private Category category;
*/

    private Boolean published;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Module> module;

    @OneToMany
    private List<Enrollment> enrollment;

    @OneToMany
    private List<Review> review;


}