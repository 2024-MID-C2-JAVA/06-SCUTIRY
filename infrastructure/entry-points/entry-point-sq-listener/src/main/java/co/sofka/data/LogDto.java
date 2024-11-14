package co.sofka.data;

public class LogDto {

    private String message;

    public LogDto(String message) {
        this.message = message;
    }

    public LogDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
