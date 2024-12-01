package com.example.banco_hex_yoder.dtos.depositoDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositoResponseDTO {

    private String cuentaOrigen;
    private BigDecimal saldoActual;
    private Detalle detalle;

    public DepositoResponseDTO() {}

    public DepositoResponseDTO(String cuentaOrigen, BigDecimal saldoActual, Detalle detalle) {
        this.cuentaOrigen = cuentaOrigen;
        this.saldoActual = saldoActual;
        this.detalle = detalle;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaDestino) {
        this.cuentaOrigen = cuentaDestino;
    }

    public BigDecimal getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(BigDecimal saldoActual) {
        this.saldoActual = saldoActual;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }

    public static class Detalle {
        private BigDecimal montoDeposito;
        private BigDecimal costoDeposito;
        private String tipoDeposito;
        private String cuentaOrigen;
        private String cuentaDestino;
        private LocalDateTime fechaTransaccion;

        public Detalle() {}

        public Detalle(BigDecimal montoDeposito, BigDecimal costoDeposito, String tipoDeposito, String cuentaOrigen, String cuentaDestino, LocalDateTime fechaTransaccion) {
            this.montoDeposito = montoDeposito;
            this.costoDeposito = costoDeposito;
            this.tipoDeposito = tipoDeposito;
            this.cuentaOrigen = cuentaOrigen;
            this.cuentaDestino = cuentaDestino;
            this.fechaTransaccion = fechaTransaccion;
        }

        public BigDecimal getMontoDeposito() {
            return montoDeposito;
        }

        public void setMontoDeposito(BigDecimal montoDeposito) {
            this.montoDeposito = montoDeposito;
        }

        public BigDecimal getCostoDeposito() {
            return costoDeposito;
        }

        public void setCostoDeposito(BigDecimal costoDeposito) {
            this.costoDeposito = costoDeposito;
        }

        public String getTipoDeposito() {
            return tipoDeposito;
        }

        public void setTipoDeposito(String tipoDeposito) {
            this.tipoDeposito = tipoDeposito;
        }

        public String getCuentaOrigen() {
            return cuentaOrigen;
        }

        public void setCuentaOrigen(String cuentaOrigen) {
            this.cuentaOrigen = cuentaOrigen;
        }

        public String getCuentaDestino() {
            return cuentaDestino;
        }

        public void setCuentaDestino(String cuentaDestino) {
            this.cuentaDestino = cuentaDestino;
        }

        public LocalDateTime getFechaTransaccion() {
            return fechaTransaccion;
        }

        public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
            this.fechaTransaccion = fechaTransaccion;
        }
    }
}
