package com.example.banco_hex_yoder.logs_repository.data.entidades;


import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "logs")
public class LogDocument {
    private BigDecimal saldoFinal;
    private String tipoOperacion;
    private BigDecimal monto;
    private LocalDateTime fechaTransaccion;
    private String rolUsuario;
    private Integer cuentaDestino;
    private BigDecimal costoOperacion;
    private Integer cuentaOrigen;
    private String detalles;

    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(BigDecimal saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Integer getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Integer cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public BigDecimal getCostoOperacion() {
        return costoOperacion;
    }

    public void setCostoOperacion(BigDecimal costoOperacion) {
        this.costoOperacion = costoOperacion;
    }

    public Integer getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Integer cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
