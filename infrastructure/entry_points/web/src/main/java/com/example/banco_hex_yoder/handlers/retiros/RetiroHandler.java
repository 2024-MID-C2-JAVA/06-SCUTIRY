package com.example.banco_hex_yoder.handlers.retiros;

import com.example.banco_hex_yoder.common.exceptions.InsufficientFundsException;
import com.example.banco_hex_yoder.din.request.DinRequest;
import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.din.generic.DinError;
import com.example.banco_hex_yoder.dtos.retiroDTO.RetiroRequestDTO;
import com.example.banco_hex_yoder.dtos.retiroDTO.RetiroResponseDTO;
import com.example.banco_hex_yoder.encriptacion.EncripcionService;
import com.example.banco_hex_yoder.gateway.AccountGateway;
import com.example.banco_hex_yoder.model.Account;
import com.example.banco_hex_yoder.usecase.retiros.RetiroEnCajero;
import com.example.banco_hex_yoder.common.exceptions.AccountNotFoundException;
import com.example.banco_hex_yoder.common.exceptions.EncryptionException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class RetiroHandler {

    private final RetiroEnCajero retiroCajero;
    private final AccountGateway accountGateway;
    private final EncripcionService encripcionService;

    public RetiroHandler(RetiroEnCajero retiroCajero, AccountGateway accountGateway, EncripcionService encripcionService) {
        this.retiroCajero = retiroCajero;
        this.accountGateway = accountGateway;
        this.encripcionService = encripcionService;
    }

    private RetiroResponseDTO.Detalle createDetalle(BigDecimal monto, BigDecimal costo, String tipoRetiro, String cuentaOrigen) {
        return new RetiroResponseDTO.Detalle(monto, costo, tipoRetiro, cuentaOrigen, LocalDateTime.now());
    }

    public DinResponse<RetiroResponseDTO> ejecutarRetiroCajero(DinRequest<RetiroRequestDTO> request) {
        DinResponse<RetiroResponseDTO> response = new DinResponse<>();
        response.setDinHeader(request.getDinHeader());

        try {
            String symmetricKey = request.getDinHeader().getLlaveSimetrica();
            String initializationVector = request.getDinHeader().getVectorInicializacion();
            String username = encripcionService.desencriptar(request.getDinBody().getCustomer(), symmetricKey, initializationVector);

            Integer cuentaOrigenNumber = Integer.parseInt(encripcionService.desencriptar(request.getDinBody().getCuentaOrigen(), symmetricKey, initializationVector));
            Account cuentaOrigen = accountGateway.findByNumber(cuentaOrigenNumber)
                    .orElseThrow(() -> new AccountNotFoundException("Cuenta origen no encontrada"));

            if (!accountGateway.esCuentaDeUsuario(cuentaOrigenNumber, username)) {
                DinError error = new DinError("WARNING", "1007", "El usuario no es dueño de la cuenta origen", "Usuario no autorizado");
                response.setDinError(error);
                response.setDinBody(null);
                return response;
            }

            Integer cuentaDestinoNumber = 000000000;
            BigDecimal monto = request.getDinBody().getMonto();
            Account cuentaActualizada = retiroCajero.realizarRetiro(cuentaOrigenNumber, cuentaDestinoNumber, monto);

            RetiroResponseDTO.Detalle detalle = createDetalle(monto, new BigDecimal("1.0"), "RetiroCajero", request.getDinBody().getCuentaOrigen());
            RetiroResponseDTO responseBody = new RetiroResponseDTO(
                    encripcionService.encriptar(String.valueOf(cuentaActualizada.getNumber()), symmetricKey, initializationVector),
                    cuentaActualizada.getAmount(),
                    detalle
            );
            response.setDinBody(responseBody);

            DinError dinError = new DinError("N", "0000", "Retiro realizado exitosamente", "El saldo ha sido actualizado.");
            response.setDinError(dinError);

        } catch (EncryptionException e) {
            response.setDinError(new DinError("ERROR", "1001", "Error al desencriptar", e.getMessage()));
            response.setDinBody(null);
        } catch (AccountNotFoundException e) {
            response.setDinError(new DinError("WARNING", "1002", "Cuenta origen no encontrada", e.getMessage()));
            response.setDinBody(null);
        } catch (InsufficientFundsException e) {
            response.setDinError(new DinError("WARNING", "1003", e.getMessage(), "No hay suficientes fondos en la cuenta"));
            response.setDinBody(null);
        } catch (Exception e) {
            response.setDinError(new DinError("ERROR", "1006", "Error desconocido", e.getMessage()));
            response.setDinBody(null);
        }

        return response;
    }
}
