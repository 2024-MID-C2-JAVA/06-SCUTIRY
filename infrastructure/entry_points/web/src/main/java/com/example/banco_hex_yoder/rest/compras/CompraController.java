package com.example.banco_hex_yoder.rest.compras;

import com.example.banco_hex_yoder.din.request.DinRequest;
import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.dtos.compraDTO.CompraRequestDTO;
import com.example.banco_hex_yoder.dtos.compraDTO.CompraResponseDTO;
import com.example.banco_hex_yoder.handlers.compras.CompraHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compras")
@Tag(name = "Compras", description = "Operaciones relacionadas con compras")
public class CompraController {

    private final CompraHandler compraHandler;

    public CompraController(CompraHandler compraHandler) {
        this.compraHandler = compraHandler;
    }
    @Operation(summary = "Compra en establecimiento físico", description = "Realiza una compra en un establecimiento físico")
    @PostMapping("/fisico")
    public DinResponse<CompraResponseDTO> comprarEnFisico(@RequestBody DinRequest<CompraRequestDTO> request) {
        return compraHandler.ejecutarCompraFisico(request);
    }
    @Operation(summary = "Compra en sitio web", description = "Realiza una compra en un sitio web")
    @PostMapping("/web")
    public DinResponse<CompraResponseDTO> comprarEnWeb(@RequestBody DinRequest<CompraRequestDTO> request) {
        return compraHandler.ejecutarCompraWeb(request);
    }
}
