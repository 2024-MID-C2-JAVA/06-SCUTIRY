package com.example.banco_hex_yoder.rest.cuentas;

import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.dtos.accountDTO.AccountResponseDTO;
import com.example.banco_hex_yoder.handlers.cuentas.ObtenerCuentasHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "cuentas", description = "Obtener la información de las cuentas (de todas como ADMIN y una en específico como USER y VIP)")
@RequestMapping("/api/data_cuentas")
public class AccountController {

    private final ObtenerCuentasHandler obtenerCuentasHandler;

    public AccountController(ObtenerCuentasHandler obtenerCuentasHandler) {
        this.obtenerCuentasHandler = obtenerCuentasHandler;
    }

    @Operation(summary = "Datos de las cuentas", description = "Obtiene la información de todas las cuentas o filtra por cliente")
    @PostMapping("/cuentas")
    public DinResponse<List<AccountResponseDTO>> getCuentas(@RequestBody(required = false) Map<String, Object> body) {
        // Valores predeterminados
        String customer = "all";
        int customerId = -1;

        // Validar y asignar valores del cuerpo de la solicitud
        if (body != null) {
            if (body.containsKey("customer")) {
                customer = body.get("customer").toString();
            }
            if (body.containsKey("customerId")) {
                try {
                    customerId = Integer.parseInt(body.get("customerId").toString());
                } catch (NumberFormatException e) {
                    customerId = -1; // En caso de datos inválidos
                }
            }
        }

        // Pasar los valores al handler
        return obtenerCuentasHandler.obtenerCuentas(customer, customerId);
    }
}
