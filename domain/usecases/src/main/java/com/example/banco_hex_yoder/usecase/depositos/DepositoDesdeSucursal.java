package com.example.banco_hex_yoder.usecase.depositos;

import com.example.banco_hex_yoder.model.Account;
import com.example.banco_hex_yoder.gateway.AccountGateway;
import com.example.banco_hex_yoder.gateway.Bus;

import java.math.BigDecimal;

public class DepositoDesdeSucursal {

    private final AccountGateway accountGateway;
    private final BigDecimal costoDepositoSucursal;
    private final Bus bus;

    public DepositoDesdeSucursal(AccountGateway accountGateway, BigDecimal costoDepositoSucursal, Bus bus) {
        this.accountGateway = accountGateway;
        this.costoDepositoSucursal = costoDepositoSucursal;
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

        BigDecimal montoFinal = monto.add(costoDepositoSucursal);
        cuentaOrigen.setAmount(cuentaOrigen.getAmount().subtract(montoFinal));
        cuentaDestino.setAmount(cuentaDestino.getAmount().add(monto));

        accountGateway.save(cuentaOrigen);
        accountGateway.save(cuentaDestino);

        try {

            accountGateway.registrarTransaccion(monto, costoDepositoSucursal, "DepositoSucursal", cuentaOrigenNumber, cuentaDestinoNumber);

            bus.sendTransactionLog("DEPOSITO_SUCURSAL", cuentaOrigenNumber, cuentaDestinoNumber, monto, costoDepositoSucursal, cuentaOrigen.getAmount(), "Depósito en sucursal desde la cuenta " + cuentaOrigenNumber + " hacia la cuenta " + cuentaDestinoNumber);

        } catch (Exception e) {
            String errorMessage = "Error en la operación de depósito desde sucursal. ";
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
