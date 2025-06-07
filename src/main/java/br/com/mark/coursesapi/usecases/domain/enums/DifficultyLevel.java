package br.com.mark.coursesapi.usecases.domain.enums;

public enum DifficultyLevel {

    BEGINNER("Iniciante"),

    INTERMEDIATE("Intermediário"),

    ADVANCED("Avançado");

    private final String level;

    DifficultyLevel(String label) {
        this.level = label;
    }

    public String getLevel() {
        return level;
    }

}
