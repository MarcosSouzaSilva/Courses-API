package br.com.mark.coursesapi.usecases.domain;

import br.com.mark.coursesapi.usecases.domain.enums.PaymentStatus;
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
public class PaymentDomain {



    PaymentStatus status; // PENDING, CONFIRMED, FAILED

    String externalTransactionId;

    BigDecimal amount;

    String method; // PIX, cartão, boleto

    LocalDateTime paidAt;

    @OneToOne
    private EnrollmentDomain enrollments;

    //integracao com mercado pago ou outro



}
