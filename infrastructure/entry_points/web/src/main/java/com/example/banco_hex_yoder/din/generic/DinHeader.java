package com.example.banco_hex_yoder.din.generic;

public class DinHeader {
    private String dispositivo;
    private String idioma;
    private String uuid;
    private String ip;
    private String horaTransaccion;
    private String llaveSimetrica;
    private String vectorInicializacion;

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHoraTransaccion() {
        return horaTransaccion;
    }

    public void setHoraTransaccion(String horaTransaccion) {
        this.horaTransaccion = horaTransaccion;
    }

    public String getLlaveSimetrica() {
        return llaveSimetrica;
    }

    public void setLlaveSimetrica(String llaveSimetrica) {
        this.llaveSimetrica = llaveSimetrica;
    }

    public String getVectorInicializacion() {
        return vectorInicializacion;
    }

    public void setVectorInicializacion(String vectorInicializacion) {
        this.vectorInicializacion = vectorInicializacion;
    }
}
