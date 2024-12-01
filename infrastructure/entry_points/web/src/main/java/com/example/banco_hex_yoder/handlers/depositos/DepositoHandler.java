package com.example.banco_hex_yoder.handlers.depositos;

import com.example.banco_hex_yoder.common.exceptions.InsufficientFundsException;
import com.example.banco_hex_yoder.din.request.DinRequest;
import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.din.generic.DinError;
import com.example.banco_hex_yoder.dtos.depositoDTO.DepositoRequestDTO;
import com.example.banco_hex_yoder.dtos.depositoDTO.DepositoResponseDTO;
import com.example.banco_hex_yoder.encriptacion.EncripcionService;
import com.example.banco_hex_yoder.gateway.AccountGateway;
import com.example.banco_hex_yoder.model.Account;
import com.example.banco_hex_yoder.usecase.depositos.DepositoDesdeCajero;
import com.example.banco_hex_yoder.usecase.depositos.DepositoDesdeOtraCuenta;
import com.example.banco_hex_yoder.usecase.depositos.DepositoDesdeSucursal;
import com.example.banco_hex_yoder.common.exceptions.AccountNotFoundException;
import com.example.banco_hex_yoder.common.exceptions.EncryptionException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class DepositoHandler {

    private final DepositoDesdeSucursal depositoDesdeSucursal;
    private final DepositoDesdeCajero depositoDesdeCajero;
    private final DepositoDesdeOtraCuenta depositoDesdeOtraCuenta;
    private final AccountGateway accountGateway;
    private final EncripcionService encripcionService;

    public DepositoHandler(DepositoDesdeSucursal depositoDesdeSucursal,
                           DepositoDesdeCajero depositoDesdeCajero,
                           DepositoDesdeOtraCuenta depositoDesdeOtraCuenta,
                           AccountGateway accountGateway,
                           EncripcionService encripcionService) {
        this.depositoDesdeSucursal = depositoDesdeSucursal;
        this.depositoDesdeCajero = depositoDesdeCajero;
        this.depositoDesdeOtraCuenta = depositoDesdeOtraCuenta;
        this.accountGateway = accountGateway;
        this.encripcionService = encripcionService;
    }

    private DepositoResponseDTO.Detalle createDetalle(BigDecimal monto, BigDecimal costo, String tipoDeposito, String cuentaOrigen, String cuentaDestino) {
        return new DepositoResponseDTO.Detalle(monto, costo, tipoDeposito, cuentaOrigen, cuentaDestino, LocalDateTime.now());
    }

    public DinResponse<DepositoResponseDTO> ejecutarDepositoSucursal(DinRequest<DepositoRequestDTO> request) {
        DinResponse<DepositoResponseDTO> response = new DinResponse<>();
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

            Integer cuentaDestinoNumber = Integer.parseInt(encripcionService.desencriptar(request.getDinBody().getCuentaDestino(), symmetricKey, initializationVector));
            BigDecimal monto = request.getDinBody().getMonto();
            Account cuentaDestinoActualizada = depositoDesdeSucursal.realizarDeposito(cuentaOrigenNumber, cuentaDestinoNumber, monto);

            DepositoResponseDTO.Detalle detalle = createDetalle(monto, new BigDecimal("0.0"), "DepositoSucursal", request.getDinBody().getCuentaOrigen(), request.getDinBody().getCuentaDestino());
            DepositoResponseDTO responseBody = new DepositoResponseDTO(
                    encripcionService.encriptar(String.valueOf(cuentaDestinoActualizada.getNumber()), symmetricKey, initializationVector),
                    cuentaDestinoActualizada.getAmount(),
                    detalle
            );
            response.setDinBody(responseBody);

            DinError dinError = new DinError("N", "0000", "Depósito realizado exitosamente", "El saldo ha sido actualizado.");
            response.setDinError(dinError);

        } catch (EncryptionException e) {
            response.setDinError(new DinError("ERROR", "1001", "Error al desencriptar", e.getMessage()));
            response.setDinBody(null);
        } catch (InsufficientFundsException e) {
            response.setDinError(new DinError("WARNING", "1003", e.getMessage(), "No hay suficientes fondos en la cuenta"));
            response.setDinBody(null);

        } catch (AccountNotFoundException e) {
            response.setDinError(new DinError("WARNING", "1002", "Cuenta origen no encontrada", e.getMessage()));
            response.setDinBody(null);
        } catch (Exception e) {
            response.setDinError(new DinError("ERROR", "1006", "Error desconocido", e.getMessage()));
            response.setDinBody(null);
        }
        return response;
    }

    public DinResponse<DepositoResponseDTO> ejecutarDepositoCajero(DinRequest<DepositoRequestDTO> request) {
        DinResponse<DepositoResponseDTO> response = new DinResponse<>();
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

            Integer cuentaDestinoNumber = Integer.parseInt(encripcionService.desencriptar(request.getDinBody().getCuentaDestino(), symmetricKey, initializationVector));
            BigDecimal monto = request.getDinBody().getMonto();
            Account cuentaDestinoActualizada = depositoDesdeCajero.realizarDeposito(cuentaOrigenNumber, cuentaDestinoNumber, monto);

            DepositoResponseDTO.Detalle detalle = createDetalle(monto, new BigDecimal("2.0"), "DepositoCajero", request.getDinBody().getCuentaOrigen(), request.getDinBody().getCuentaDestino());
            DepositoResponseDTO responseBody = new DepositoResponseDTO(
                    encripcionService.encriptar(String.valueOf(cuentaDestinoActualizada.getNumber()), symmetricKey, initializationVector),
                    cuentaDestinoActualizada.getAmount(),
                    detalle
            );
            response.setDinBody(responseBody);

            DinError dinError = new DinError("N", "0000", "Depósito realizado exitosamente", "El saldo ha sido actualizado.");
            response.setDinError(dinError);

        } catch (EncryptionException e) {
            response.setDinError(new DinError("ERROR", "1001", "Error al desencriptar", e.getMessage()));
            response.setDinBody(null);
        } catch (InsufficientFundsException e) {
            response.setDinError(new DinError("WARNING", "1003", e.getMessage(), "No hay suficientes fondos en la cuenta"));
            response.setDinBody(null);
        } catch (AccountNotFoundException e) {
            response.setDinError(new DinError("WARNING", "1002", "Cuenta origen no encontrada", e.getMessage()));
            response.setDinBody(null);
        } catch (Exception e) {
            response.setDinError(new DinError("ERROR", "1006", "Error desconocido", e.getMessage()));
            response.setDinBody(null);
        }
        return response;
    }

    public DinResponse<DepositoResponseDTO> ejecutarDepositoOtraCuenta(DinRequest<DepositoRequestDTO> request) {
        DinResponse<DepositoResponseDTO> response = new DinResponse<>();
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

            Integer cuentaDestinoNumber = Integer.parseInt(encripcionService.desencriptar(request.getDinBody().getCuentaDestino(), symmetricKey, initializationVector));
            BigDecimal monto = request.getDinBody().getMonto();
            Account cuentaDestinoActualizada = depositoDesdeOtraCuenta.realizarDeposito(cuentaOrigenNumber, cuentaDestinoNumber, monto);

            DepositoResponseDTO.Detalle detalle = createDetalle(monto, new BigDecimal("1.5"), "DepositoOtraCuenta", request.getDinBody().getCuentaOrigen(), request.getDinBody().getCuentaDestino());
            DepositoResponseDTO responseBody = new DepositoResponseDTO(
                    encripcionService.encriptar(String.valueOf(cuentaDestinoActualizada.getNumber()), symmetricKey, initializationVector),
                    cuentaDestinoActualizada.getAmount(),
                    detalle
            );
            response.setDinBody(responseBody);

            DinError dinError = new DinError("N", "0000", "Depósito realizado exitosamente", "El saldo ha sido actualizado.");
            response.setDinError(dinError);

        } catch (EncryptionException e) {
            response.setDinError(new DinError("ERROR", "1001", "Error al desencriptar", e.getMessage()));
            response.setDinBody(null);
        } catch (InsufficientFundsException e) {
            response.setDinError(new DinError("WARNING", "1003", e.getMessage(), "No hay suficientes fondos en la cuenta"));
            response.setDinBody(null);
        } catch (AccountNotFoundException e) {
            response.setDinError(new DinError("WARNING", "1002", "Cuenta origen no encontrada", e.getMessage()));
            response.setDinBody(null);
        } catch (Exception e) {
            response.setDinError(new DinError("ERROR", "1006", "Error desconocido", e.getMessage()));
            response.setDinBody(null);
        }
        return response;
    }
}
