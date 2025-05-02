package br.com.mark.coursesapi.dataprovider.entity;

import br.com.mark.coursesapi.usecases.domain.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status; // PENDING, CONFIRMED, FAILED

    private String externalTransactionId;

    private BigDecimal amount;

    private String method; // PIX, cartão, boleto

    private LocalDateTime paidAt;

    @OneToOne
    private Enrollment enrollments;

    //integracao com mercado pago ou outro



}
