package br.com.mark.coursesapi.usecases.domain;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProgressDomain {



    Boolean completed;

    LocalDateTime completedAt;

    @ManyToOne
    private List<UserDomain> userDomain;

    @ManyToOne
    private List<LessonDomain> lessonDomains;

}