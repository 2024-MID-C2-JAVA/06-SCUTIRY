package co.sofka.data;

public class LogDtoListener {

    private String message;

    public LogDtoListener(String message) {
        this.message = message;
    }

    public LogDtoListener() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
