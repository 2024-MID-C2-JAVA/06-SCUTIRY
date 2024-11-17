package com.example.banco_hex_yoder.rest.logs;


import com.example.banco_hex_yoder.din.request.DinRequest;
import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.dtos.logs.LogFilterDTO;
import com.example.banco_hex_yoder.logs_repository.data.entidades.LogDocument;
import com.example.banco_hex_yoder.handlers.logs.ObtenerLogsHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@Tag(name = "logs", description = "Optiene los solo con rol: OSI")
public class LogController {

    private final ObtenerLogsHandler obtenerLogsHandler;

    @Autowired
    public LogController(ObtenerLogsHandler obtenerLogsHandler) {
        this.obtenerLogsHandler = obtenerLogsHandler;
    }

    @PostMapping
    public DinResponse<List<LogDocument>> getLogs(@RequestBody DinRequest<LogFilterDTO> request) {
        return obtenerLogsHandler.obtenerLogs(request);
    }
}
