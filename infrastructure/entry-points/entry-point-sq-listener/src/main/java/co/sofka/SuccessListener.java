package co.sofka;



import co.sofka.data.LogDtoListener;
import co.sofka.handler.LogHandler;
import co.sofka.rabbitMq.BusListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class SuccessListener implements BusListener {

    private final LogHandler logHandler;

    public SuccessListener(LogHandler logHandler) {
        this.logHandler = logHandler;
    }

    @Override
    @RabbitListener(queues = "queue.success")
    public void receiveMessage(String message) {
        LogDtoListener dto=new LogDtoListener(message);
        logHandler.saveLog(dto);
    }

}
