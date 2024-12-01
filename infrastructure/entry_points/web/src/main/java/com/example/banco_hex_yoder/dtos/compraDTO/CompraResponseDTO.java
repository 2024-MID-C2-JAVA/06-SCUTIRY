package com.example.banco_hex_yoder.dtos.compraDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CompraResponseDTO {
    private String cuentaOrigen;
    private BigDecimal saldoActual;
    private Detalle detalle;

    public CompraResponseDTO() {}

    public CompraResponseDTO(String cuentaOrigen, BigDecimal saldoActual, Detalle detalle) {
        this.cuentaOrigen = cuentaOrigen;
        this.saldoActual = saldoActual;
        this.detalle = detalle;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigenEncriptada) {
        this.cuentaOrigen = cuentaOrigenEncriptada;
    }

    public BigDecimal getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(BigDecimal saldoActualizado) {
        this.saldoActual = saldoActualizado;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }

    public static class Detalle {
        private BigDecimal montoCompra;
        private BigDecimal costoCompra;
        private String tipoCompra;
        private String cuentaOrigen;
        private LocalDateTime fechaTransaccion;

        public Detalle() {}

        public Detalle(BigDecimal montoCompra, BigDecimal costoCompra, String tipoCompra, String cuentaOrigen, LocalDateTime fechaTransaccion) {
            this.montoCompra = montoCompra;
            this.costoCompra = costoCompra;
            this.tipoCompra = tipoCompra;
            this.cuentaOrigen = cuentaOrigen;
            this.fechaTransaccion = fechaTransaccion;
        }

        public BigDecimal getMontoCompra() {
            return montoCompra;
        }

        public void setMontoCompra(BigDecimal montoCompra) {
            this.montoCompra = montoCompra;
        }

        public BigDecimal getCostoCompra() {
            return costoCompra;
        }

        public void setCostoCompra(BigDecimal costoCompra) {
            this.costoCompra = costoCompra;
        }

        public String getTipoCompra() {
            return tipoCompra;
        }

        public void setTipoCompra(String tipoCompra) {
            this.tipoCompra = tipoCompra;
        }

        public String getCuentaOrigen() {
            return cuentaOrigen;
        }

        public void setCuentaOrigen(String cuentaOrigen) {
            this.cuentaOrigen = cuentaOrigen;
        }

        public LocalDateTime getFechaTransaccion() {
            return fechaTransaccion;
        }

        public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
            this.fechaTransaccion = fechaTransaccion;
        }
    }
}
