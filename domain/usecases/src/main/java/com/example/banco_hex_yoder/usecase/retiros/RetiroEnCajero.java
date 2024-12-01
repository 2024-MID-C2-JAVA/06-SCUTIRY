package com.example.banco_hex_yoder.usecase.retiros;

import com.example.banco_hex_yoder.gateway.Bus;
import com.example.banco_hex_yoder.model.Account;
import com.example.banco_hex_yoder.gateway.AccountGateway;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

public class RetiroEnCajero {

    private final AccountGateway accountGateway;
    private final BigDecimal costoRetiro;
    private final Bus bus;


    public RetiroEnCajero(AccountGateway accountGateway, BigDecimal costoRetiro, Bus bus) {
        this.accountGateway = accountGateway;
        this.costoRetiro = costoRetiro;
        this.bus=bus;
    }

    public Account realizarRetiro(Integer cuentaOrigenNumber, Integer cuentaDestinoNumber, BigDecimal monto) throws AccountNotFoundException {
        Account cuentaOrigen = accountGateway.findByNumber(cuentaOrigenNumber)
                .orElseThrow(() -> new AccountNotFoundException("Cuenta origen no encontrada"));

        BigDecimal montoTotal = monto.add(costoRetiro);

        if (cuentaOrigen.getAmount().compareTo(montoTotal) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar el retiro");
        }

        cuentaOrigen.setAmount(cuentaOrigen.getAmount().subtract(montoTotal));


        accountGateway.save(cuentaOrigen);


        try {

            accountGateway.registrarTransaccion(monto, costoRetiro, "RetiroCajero", cuentaOrigenNumber, cuentaDestinoNumber);


            bus.sendTransactionLog("RETIRO_CAJERO", cuentaOrigenNumber, cuentaDestinoNumber, monto, costoRetiro, cuentaOrigen.getAmount(), "Retiro en cajero realizado desde la cuenta " + cuentaOrigenNumber);

        } catch (Exception e) {

            String errorMessage = "Error en la operación de retiro. ";
            if (e.getMessage().contains("registrarTransaccion")) {
                errorMessage += "Fallo al registrar la transacción: " + e.getMessage();
            } else if (e.getMessage().contains("sendTransactionLog")) {
                errorMessage += "Fallo al enviar el log de transacción a rabbit: " + e.getMessage();
            } else {
                errorMessage += "Error desconocido: " + e.getMessage();
            }


            throw new RuntimeException(errorMessage, e);
        }




        return cuentaOrigen;
    }
}
