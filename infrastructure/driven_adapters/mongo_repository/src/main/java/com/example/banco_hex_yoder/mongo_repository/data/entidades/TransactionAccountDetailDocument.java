package com.example.banco_hex_yoder.mongo_repository.data.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "transaction_account_detail")
public class TransactionAccountDetailDocument {

    @Id
    private String id;
    private String transactionId;
    private Integer cuentaOrigen;
    private Integer cuentaDestino;
    private BigDecimal montoDeposito;
    private BigDecimal costoDeposito;
    private String tipoDeposito;
    private String fechaTransaccion;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Integer cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Integer getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Integer cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
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

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }


    public static String obtenerFechaActual() {
        return LocalDateTime.now().toString();
    }
}
