package co.sofka;

import co.sofka.data.LogDto;
import co.sofka.handler.LogHandler;
import co.sofka.rabbitMq.BusListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ErrorListener implements BusListener {

    private final LogHandler logHandler;

    public ErrorListener(LogHandler logHandler) {
        this.logHandler = logHandler;
    }


    @Override
    @RabbitListener(queues = "queue.error")
    public void receiveMessage(String message) {
        LogDto dto=new LogDto(message);
        logHandler.saveLog(dto);
    }
}
