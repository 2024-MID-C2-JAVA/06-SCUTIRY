package com.example.banco_hex_yoder.rest.depositos;

import com.example.banco_hex_yoder.din.request.DinRequest;
import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.dtos.depositoDTO.DepositoRequestDTO;
import com.example.banco_hex_yoder.dtos.depositoDTO.DepositoResponseDTO;
import com.example.banco_hex_yoder.handlers.depositos.DepositoHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/depositos")
@Tag(name = "depositos", description = "Operaciones relacionadas con depositos")
public class DepositoController {

    private final DepositoHandler depositoHandler;

    public DepositoController(DepositoHandler depositoHandler) {
        this.depositoHandler = depositoHandler;
    }
    @Operation(summary = "Depósito en sitio sucursal", description = "Realiza un Depósito en sitio sucursal")
    @PostMapping("/sucursal")
    public DinResponse<DepositoResponseDTO> depositarEnSucursal(@RequestBody DinRequest<DepositoRequestDTO> request) {
        return depositoHandler.ejecutarDepositoSucursal(request);
    }
    @Operation(summary = "Depósito en sitio cajero", description = "Realiza un Depósito en cajero")
    @PostMapping("/cajero")
    public DinResponse<DepositoResponseDTO> depositarEnCajero(@RequestBody DinRequest<DepositoRequestDTO> request) {
        return depositoHandler.ejecutarDepositoCajero(request);
    }
    @Operation(summary = "Depósito en otracuenta", description = "Realiza un Depósito en otracuentavvvvvv")
    @PostMapping("/otracuenta")
    public DinResponse<DepositoResponseDTO> depositarDesdeOtraCuenta(@RequestBody DinRequest<DepositoRequestDTO> request) {
        return depositoHandler.ejecutarDepositoOtraCuenta(request);
    }
}
