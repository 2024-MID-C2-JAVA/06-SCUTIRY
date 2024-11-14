package co.sofka;

import java.time.Instant;

public class Log {

    private int id;
    private String message;
    private Instant timestamp;

    private Log(int id, String message, Instant timestamp) {
        this.id = id;
        this.message = message;
        this.timestamp = Instant.now();
    }

    private Log() {
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

        public Builder setTimestamp() {
            this.timestamp = Instant.now();
            return this;
        }

        public Log build() {
            return new Log(id, message, timestamp);
        }
    }
}
