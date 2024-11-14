package co.sofka.data;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "Logs")
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;
    @Column(name = "time_stamp")
    private Instant timestamp;

    public LogEntity(int id, String message, Instant timestamp) {
        this.id = id;
        this.message = message;
        this.timestamp = Instant.now();
    }

    public LogEntity() {
        this.timestamp = Instant.now();
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
}
