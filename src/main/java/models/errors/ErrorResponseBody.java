package models.errors;

public class ErrorResponseBody {

    private String code;
    private String message;

    public ErrorResponseBody() {
    }

    public ErrorResponseBody(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
