package com.example.banco_hex_yoder.rest.usuarios;

import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.dtos.registroDTO.UsuarioResponseDTO;
import com.example.banco_hex_yoder.handlers.usuarios.ObtenerUsuariosHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/getusuarios")
@Tag(name = "getusuarios", description = "Obtener todos los usuarios")
public class UserController {

    private final ObtenerUsuariosHandler obtenerUsuariosHandler;

    public UserController(ObtenerUsuariosHandler obtenerUsuariosHandler) {
        this.obtenerUsuariosHandler = obtenerUsuariosHandler;
    }

    @PostMapping
    public DinResponse<List<UsuarioResponseDTO>> getUsuarios() {
        return obtenerUsuariosHandler.obtenerUsuarios();
    }
}
