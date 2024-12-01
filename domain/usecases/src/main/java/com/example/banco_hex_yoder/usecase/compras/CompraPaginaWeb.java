package com.example.banco_hex_yoder.usecase.compras;

import com.example.banco_hex_yoder.model.Account;
import com.example.banco_hex_yoder.gateway.AccountGateway;
import com.example.banco_hex_yoder.gateway.Bus;
import java.math.BigDecimal;

public class CompraPaginaWeb {

    private final AccountGateway accountGateway;
    private final BigDecimal costoSeguroCompraWeb;
    private final Bus bus;

    public CompraPaginaWeb(AccountGateway accountGateway, BigDecimal costoSeguroCompraWeb, Bus bus) {
        this.accountGateway = accountGateway;
        this.costoSeguroCompraWeb = costoSeguroCompraWeb;
        this.bus = bus;
    }

    public Account realizarCompra(Integer cuentaOrigenNumber, BigDecimal monto) {
        Account cuentaOrigen = accountGateway.findByNumber(cuentaOrigenNumber)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta origen no encontrada"));

        Integer cuentaDestinoNumber = 00000000;
        BigDecimal montoTotal = monto.add(costoSeguroCompraWeb);

        if (cuentaOrigen.getAmount().compareTo(montoTotal) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar la compra");
        }

        cuentaOrigen.setAmount(cuentaOrigen.getAmount().subtract(montoTotal));
        accountGateway.save(cuentaOrigen);

        try {

            accountGateway.registrarTransaccion(monto, costoSeguroCompraWeb, "CompraPaginaWeb", cuentaOrigenNumber, cuentaDestinoNumber);

            bus.sendTransactionLog("COMPRA_PAGINA_WEB", cuentaOrigenNumber, cuentaDestinoNumber, monto, costoSeguroCompraWeb, cuentaOrigen.getAmount(),
                    "Compra en página web realizada desde la cuenta " + cuentaOrigenNumber);

        } catch (Exception e) {
            String errorMessage = "Error en la operación de compra en página web. ";
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
