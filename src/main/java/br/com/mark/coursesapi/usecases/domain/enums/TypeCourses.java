package br.com.mark.coursesapi.usecases.domain.enums;

public enum TypeCourses {

    PROGRAMMING_DEVELOPMENT("Programming & Development"),
    DESIGN_MULTIMEDIA("Design & Multimedia"),
    MARKETING_SALES("Marketing & Sales"),
    BUSINESS_ENTREPRENEURSHIP("Business & Entrepreneurship"),
    TECHNOLOGY_DATA("Technology & Data");

    private final String course;

    TypeCourses(String label) {
        this.course = label;
    }

    public String getCourse() {
        return course;
    }
}
