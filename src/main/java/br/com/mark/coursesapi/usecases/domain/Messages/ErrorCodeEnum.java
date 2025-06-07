package br.com.mark.coursesapi.usecases.domain.Messages;

public enum ErrorCodeEnum {


    A0001("User not found. Try again."),
    A0002("Please enter a valid email address. It should contain a username, the '@' symbol, and a valid domain (e.g., example@domain.com)."),
    A0003("Invalid credentials"),
    A0004("Your birthdate is invalid. Try again."),
    A0005("Error creating your account. Check the information and try again."),
    A0006("Please enter a valid phone number. Expected format: (XX)XXXXX-XXXX or similar. Make sure to include the area code and a 9- or 8-digit number."),
    A0007("Email or number is already used.Try again."),
    A0008("The token who was type is invalid.Try again."),
    A0009("Access denied: You do not have permission to access this resource."),
    A00010("Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character (@$!%*?&)."),
    A00011("The token is invalid or the user associated with this token does not have permission.");

    private final String message;

    ErrorCodeEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}