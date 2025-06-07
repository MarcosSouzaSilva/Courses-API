package br.com.mark.coursesapi.dataprovider.entity;

import br.com.mark.coursesapi.usecases.domain.enums.DifficultyLevel;
import br.com.mark.coursesapi.usecases.domain.enums.TypeCourses;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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

    @Lob // Informa ao JPA que esse campo será armazenado como um tipo binário grande (BLOB no banco)
    private byte[] video; // Representa dados binários. O conteúdo já deve estar convertido em binário (byte[]) antes de ser atribuído

    @Lob
    private byte[] image;

    private BigDecimal price;

    @Lob
    private byte[] thumbnailUrl;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel level; // BEGINNER, INTERMEDIATE, ADVANCED

    private TypeCourses typeCourses;

    private Boolean published;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    private Teachers teachers;

    @OneToMany
    private List<Module> module;

    @OneToMany
    private List<Enrollment> enrollment;

    @OneToMany
    private List<Review> review;


}