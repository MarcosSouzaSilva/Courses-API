package br.com.mark.coursesapi.usecases.interfaces;

public interface EmailUseCase {

    void sendEmail(String to, String subject, String message);
}