package br.com.mark.coursesapi.usecases.domain.enums;

public enum PaymentStatus {

    PENDING("Pendente"),

    CONFIRMED("Confirmado"),

    FAILED("Falhou");

    private final String label;

    PaymentStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}