package com.example.banco_hex_yoder.gateway;

import java.math.BigDecimal;

public interface Bus {
    void sendMessage(String message);
    void sendTransactionLog(String tipoOperacion, Integer cuentaOrigen, Integer cuentaDestino, BigDecimal monto, BigDecimal costoOperacion, BigDecimal saldoFinal, String detalles);
}
