package pw.react.backend.reactbackend.Errors;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        this("");
    }

    public UserNotFound(String message) {
        super(message);
    }
}
