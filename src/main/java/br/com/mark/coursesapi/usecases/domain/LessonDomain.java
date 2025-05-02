package br.com.mark.coursesapi.usecases.domain;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LessonDomain {



    String title;

    String description;

    //LessonType type; // VIDEO, PDF, QUIZ

    String contentUrl;

    Integer order;

    @ManyToOne
    private List<ModuleDomain> moduleDomains;



}