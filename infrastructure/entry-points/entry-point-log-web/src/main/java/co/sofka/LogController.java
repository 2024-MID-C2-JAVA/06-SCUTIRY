package co.sofka;

import co.sofka.data.LogDto;
import co.sofka.data.ResponseLogMs;
import co.sofka.handler.Handler;
import din.DinError;
import din.DinErrorEnum;
import din.RequestMs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
public class LogController {


    private final Handler handler;

    public LogController(Handler handler) {
        this.handler = handler;
    }

    @PostMapping("/get")
    public ResponseEntity<ResponseLogMs> getLogs(@RequestBody RequestMs<LogDto> request) {
        return ResponseEntity.ok(new ResponseLogMs(request.getDinHeader(),handler.getLogs(),new DinError(DinErrorEnum.SUCCESS)));
    }
}
