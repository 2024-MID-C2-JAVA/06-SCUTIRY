package co.sofka.data;

import java.time.Instant;

public class LogDto {
    private int id;
    private String message;
    private Instant timestamp;

    private LogDto(int id, String message, Instant timestamp) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public static class Builder {
        private int id;
        private String message;
        private Instant timestamp;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public LogDto build() {
            return new LogDto(id, message, timestamp);
        }
    }
}
