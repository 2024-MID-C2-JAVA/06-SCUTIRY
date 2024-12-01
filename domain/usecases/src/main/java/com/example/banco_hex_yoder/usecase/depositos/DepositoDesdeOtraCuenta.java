package com.example.banco_hex_yoder.usecase.depositos;

import com.example.banco_hex_yoder.model.Account;
import com.example.banco_hex_yoder.gateway.AccountGateway;
import com.example.banco_hex_yoder.gateway.Bus;
import java.math.BigDecimal;

public class DepositoDesdeOtraCuenta {

    private final AccountGateway accountGateway;
    private final BigDecimal costoDepositoOtraCuenta;
    private final Bus bus;

    public DepositoDesdeOtraCuenta(AccountGateway accountGateway, BigDecimal costoDepositoOtraCuenta, Bus bus) {
        this.accountGateway = accountGateway;
        this.costoDepositoOtraCuenta = costoDepositoOtraCuenta;
        this.bus = bus;
    }

    public Account realizarDeposito(Integer cuentaOrigenNumber, Integer cuentaDestinoNumber, BigDecimal monto) {
        Account cuentaOrigen = accountGateway.findByNumber(cuentaOrigenNumber)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta origen no encontrada"));
        Account cuentaDestino = accountGateway.findByNumber(cuentaDestinoNumber)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta destino no encontrada"));

        if (cuentaOrigen.getAmount().compareTo(monto) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar el depósito");
        }

        BigDecimal montoFinal = monto.add(costoDepositoOtraCuenta);
        cuentaOrigen.setAmount(cuentaOrigen.getAmount().subtract(montoFinal));
        cuentaDestino.setAmount(cuentaDestino.getAmount().add(monto));

        accountGateway.save(cuentaOrigen);
        accountGateway.save(cuentaDestino);

        try {

            accountGateway.registrarTransaccion(monto, costoDepositoOtraCuenta, "DepositoOtraCuenta", cuentaOrigenNumber, cuentaDestinoNumber);

            bus.sendTransactionLog("DEPOSITO_OTRA_CUENTA", cuentaOrigenNumber, cuentaDestinoNumber, monto, costoDepositoOtraCuenta, cuentaOrigen.getAmount(),
                    "Depósito desde otra cuenta realizado desde la cuenta " + cuentaOrigenNumber + " hacia la cuenta " + cuentaDestinoNumber);

        } catch (Exception e) {
            String errorMessage = "Error en la operación de depósito desde otra cuenta. ";
            if (e.getMessage().contains("registrarTransaccion")) {
                errorMessage += "Fallo al registrar la transacción: " + e.getMessage();
            } else if (e.getMessage().contains("sendTransactionLog")) {
                errorMessage += "Fallo al enviar el log de transacción a RabbitMQ: " + e.getMessage();
            } else {
                errorMessage += "Error desconocido: " + e.getMessage();
            }

            throw new RuntimeException(errorMessage, e);
        }

        return cuentaOrigen;
    }
}
