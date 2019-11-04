package pw.react.backend.reactbackend.Errors;

public class DoubleLoginException extends RuntimeException {
    public DoubleLoginException() {
        this("");
    }

    public DoubleLoginException(String message) {
        super(message);
    }
}
