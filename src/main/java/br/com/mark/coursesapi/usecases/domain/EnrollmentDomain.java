package br.com.mark.coursesapi.usecases.domain;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class EnrollmentDomain {

    //a classe representa a compra de um curso por um aluno


    LocalDateTime enrolledAt;

    Boolean completed;

    @ManyToOne
    private List<UserDomain> userDomain;

    @ManyToOne
    private List<CoursesDomain> cours;

    @OneToOne
    private PaymentDomain payments;



}