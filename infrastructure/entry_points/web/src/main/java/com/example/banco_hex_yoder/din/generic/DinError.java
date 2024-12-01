package com.example.banco_hex_yoder.din.generic;

import java.time.LocalDateTime;

public class DinError {
    private String tipo;
    private String fecha;
    private String origen;
    private String codigo;
    private String codigoErrorProveedor;
    private String mensaje;
    private String detalle;

    public DinError(String tipo, String codigo, String mensaje, String detalle) {
        this.tipo = tipo;
        this.fecha = LocalDateTime.now().toString();
        this.origen = "api-bank-management-instance-1";
        this.codigo = codigo;
        this.codigoErrorProveedor = codigo;
        this.mensaje = mensaje;
        this.detalle = detalle;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoErrorProveedor() {
        return codigoErrorProveedor;
    }

    public void setCodigoErrorProveedor(String codigoErrorProveedor) {
        this.codigoErrorProveedor = codigoErrorProveedor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
