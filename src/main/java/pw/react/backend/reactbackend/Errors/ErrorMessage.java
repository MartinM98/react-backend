package pw.react.backend.reactbackend.Errors;

public class ErrorMessage {
    private String message;
    private int code;
    private String Info;

    public ErrorMessage(String message, int code, String Info) {
        this.message = message;
        this.code = code;
        this.Info = Info;
    }

    public ErrorMessage(String message, int code) {
        this(message, code, "");
    }

    public ErrorMessage(int code) {
        this("", code, "");
    }


    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String Info() {
        return Info;
    }
}
