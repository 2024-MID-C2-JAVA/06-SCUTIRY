package com.example.banco_hex_yoder.config.usecase;

import com.example.banco_hex_yoder.gateway.AccountGateway;
import com.example.banco_hex_yoder.gateway.Bus;
import com.example.banco_hex_yoder.usecase.compras.CompraEstablecimientoFisico;
import com.example.banco_hex_yoder.usecase.depositos.DepositoDesdeSucursal;
import com.example.banco_hex_yoder.usecase.depositos.DepositoDesdeCajero;
import com.example.banco_hex_yoder.usecase.depositos.DepositoDesdeOtraCuenta;
import com.example.banco_hex_yoder.usecase.compras.CompraPaginaWeb;
import com.example.banco_hex_yoder.usecase.retiros.RetiroEnCajero;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class UseCaseConfig {

    @Value("${app.costoDepositoSucursal}")
    private BigDecimal costoDepositoSucursal;

    @Value("${app.costoDepositoCajero}")
    private BigDecimal costoDepositoCajero;

    @Value("${app.costoDepositoOtraCuenta}")
    private BigDecimal costoDepositoOtraCuenta;

    @Value("${app.costoSeguroCompraWeb}")
    private BigDecimal costoSeguroCompraWeb;

    @Value("${app.costoRetiro}")
    private BigDecimal costoRetiro;

    @Value("${app.costoCompraEstablecimiento}")
    private BigDecimal costoCompraEstablecimiento;

    private final AccountGateway accountGateway;
    private final Bus bus;

    public UseCaseConfig(AccountGateway accountGateway, Bus bus) {
        this.accountGateway = accountGateway;
        this.bus = bus;
    }

    @Bean
    public DepositoDesdeSucursal depositoDesdeSucursal() {
        return new DepositoDesdeSucursal(accountGateway, costoDepositoSucursal, bus);
    }

    @Bean
    public DepositoDesdeCajero depositoDesdeCajero() {
        return new DepositoDesdeCajero(accountGateway, costoDepositoCajero, bus);
    }

    @Bean
    public DepositoDesdeOtraCuenta depositoDesdeOtraCuenta() {
        return new DepositoDesdeOtraCuenta(accountGateway, costoDepositoOtraCuenta, bus);
    }

    @Bean
    public CompraPaginaWeb compraPaginaWeb() {
        return new CompraPaginaWeb(accountGateway, costoSeguroCompraWeb, bus);
    }

    @Bean
    public RetiroEnCajero retiroEnCajero() {
        return new RetiroEnCajero(accountGateway, costoRetiro, bus);
    }

    @Bean
    public CompraEstablecimientoFisico compraEstablecimientoFisico() {
        return new CompraEstablecimientoFisico(accountGateway, costoCompraEstablecimiento,bus);
    }
}
