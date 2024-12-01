package com.example.banco_hex_yoder.rest.registros;

import com.example.banco_hex_yoder.din.request.DinRequest;
import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.din.generic.DinError;
import com.example.banco_hex_yoder.dtos.registroDTO.UsuarioRequestDTO;
import com.example.banco_hex_yoder.dtos.registroDTO.UsuarioResponseDTO;
import com.example.banco_hex_yoder.handlers.registros.CrearUsuarioHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "usuarios", description = "Operaciones relacionadas con usuarios")
public class CrearUsuarioController {

    private final CrearUsuarioHandler crearUsuarioHandler;

    public CrearUsuarioController(CrearUsuarioHandler crearUsuarioHandler) {
        this.crearUsuarioHandler = crearUsuarioHandler;
    }

    @PostMapping("/crear")
    public ResponseEntity<DinResponse<UsuarioResponseDTO>> crearUsuario(@RequestBody DinRequest<UsuarioRequestDTO> request) {
        try {
            DinResponse<UsuarioResponseDTO> response = crearUsuarioHandler.crearUsuario(request);
            if (response.getDinError().getTipo().equals("N")) { // Tipo 'N' significa éxito
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            DinResponse<UsuarioResponseDTO> errorResponse = new DinResponse<>();
            errorResponse.setDinError(new DinError("ERROR", "1006", "Error al procesar la solicitud", e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
