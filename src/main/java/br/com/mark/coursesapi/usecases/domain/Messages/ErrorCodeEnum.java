package br.com.mark.coursesapi.usecases.domain.Messages;

public enum ErrorCodeEnum {


    A0001("User not found. Try again."),
    A0002("Invalid email. Try again."),
    A0003("Invalid credentials"),
    A0004("Your birthdate is invalid. Try again."),
    A0005("Error creating your account. Check the information and try again."),
    A0006("Invalid phone number. Try again."),
    A0007("Email or number is already used.Try again."),
    A0008("The token who was type is invalid.Try again."),
    A0009("Access denied: You do not have permission to access this resource.");

    private final String message;

    ErrorCodeEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}