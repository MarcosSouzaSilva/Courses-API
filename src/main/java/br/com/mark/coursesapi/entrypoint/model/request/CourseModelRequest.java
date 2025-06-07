package br.com.mark.coursesapi.entrypoint.model.request;

import br.com.mark.coursesapi.usecases.domain.enums.DifficultyLevel;
import br.com.mark.coursesapi.usecases.domain.enums.TypeCourses;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CourseModelRequest {

    private String title;

    private String description;

    private MultipartFile video;

    private MultipartFile image;

    private BigDecimal price;

    private MultipartFile thumbnailUrl;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel level; // BEGINNER, INTERMEDIATE, ADVANCED

    @Enumerated(EnumType.STRING)
    private TypeCourses typeCourses;

    private Boolean published;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}