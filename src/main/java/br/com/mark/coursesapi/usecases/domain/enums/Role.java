package br.com.mark.coursesapi.usecases.domain.enums;

public enum Role {

    ADMIN("Administrador"),

    INSTRUCTOR("Instrutor"),

    STUDENT("Estudante");

    private final String role;

    Role(String label) {
        this.role = label;
    }

    public String getRole() {
        return role;
    }

}