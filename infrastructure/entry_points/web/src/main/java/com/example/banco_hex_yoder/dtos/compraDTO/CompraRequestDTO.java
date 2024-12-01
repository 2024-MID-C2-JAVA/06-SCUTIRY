package com.example.banco_hex_yoder.dtos.compraDTO;

import java.math.BigDecimal;

public class CompraRequestDTO {
    private String cuentaOrigen;
    private BigDecimal monto;
    private String customer;

    public CompraRequestDTO() {}

    public CompraRequestDTO(String cuentaOrigen, BigDecimal monto, String customer) {
        this.cuentaOrigen = cuentaOrigen;
        this.monto = monto;
        this.customer = customer;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
