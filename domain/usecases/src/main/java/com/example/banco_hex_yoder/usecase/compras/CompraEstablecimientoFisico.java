package com.example.banco_hex_yoder.usecase.compras;

import com.example.banco_hex_yoder.model.Account;
import com.example.banco_hex_yoder.gateway.AccountGateway;
import com.example.banco_hex_yoder.gateway.Bus;
import java.math.BigDecimal;

public class CompraEstablecimientoFisico {

    private final AccountGateway accountGateway;
    private final BigDecimal costoCompraEstablecimiento;
    private final Bus bus;

    public CompraEstablecimientoFisico(AccountGateway accountGateway, BigDecimal costoCompraEstablecimiento, Bus bus) {
        this.accountGateway = accountGateway;
        this.costoCompraEstablecimiento = costoCompraEstablecimiento;
        this.bus = bus;
    }

    public Account realizarCompra(Integer cuentaOrigenNumber, BigDecimal monto) {
        Account cuentaOrigen = accountGateway.findByNumber(cuentaOrigenNumber)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta origen no encontrada"));

        Integer cuentaDestinoNumber = 000000000; // Cuenta destino predeterminada
        BigDecimal montoTotal = monto.add(costoCompraEstablecimiento);

        if (cuentaOrigen.getAmount().compareTo(montoTotal) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar la compra");
        }

        cuentaOrigen.setAmount(cuentaOrigen.getAmount().subtract(montoTotal));
        accountGateway.save(cuentaOrigen);

        try {
            // Registrar la transacción y enviar el log
            accountGateway.registrarTransaccion(monto, costoCompraEstablecimiento, "CompraEstablecimientoFisico", cuentaOrigenNumber, cuentaDestinoNumber);

            bus.sendTransactionLog("COMPRA_ESTABLECIMIENTO_FISICO", cuentaOrigenNumber, cuentaDestinoNumber, monto, costoCompraEstablecimiento, cuentaOrigen.getAmount(),
                    "Compra en establecimiento físico realizada desde la cuenta " + cuentaOrigenNumber);

        } catch (Exception e) {
            String errorMessage = "Error en la operación de compra en establecimiento físico. ";
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
