package br.com.mark.coursesapi.dataprovider.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Enrollment {

    //a classe representa a compra de um curso por um aluno
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime enrolledAt;

    private Boolean completed;

    @ManyToOne
    private User user;

    @ManyToOne
    private Courses courses;

    @OneToOne
    private Payment payments;



}