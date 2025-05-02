package br.com.mark.coursesapi.usecases.domain.enums;

public enum DifficultyLevel {

    BEGINNER("Iniciante"),

    INTERMEDIATE("Intermediario"),

    ADVANCED("Avancado");

    private final String level;

    DifficultyLevel(String label) {
        this.level = label;
    }

    public String getLevel() {
        return level;
    }

}
