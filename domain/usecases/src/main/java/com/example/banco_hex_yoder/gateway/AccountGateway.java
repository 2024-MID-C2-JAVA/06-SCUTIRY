package com.example.banco_hex_yoder.gateway;

import com.example.banco_hex_yoder.model.Account;
import java.math.BigDecimal;
import java.util.Optional;

public interface AccountGateway {
    Optional<Account> findById(Integer id);
    void registrarTransaccion(BigDecimal monto, BigDecimal costo, String tipoTransaccion, Integer cuentaOrigenNumber, Integer cuentaDestinoNumber);
    Account save(Account account);
    Optional<Account> findByNumber(Integer number);
    void updateBalance(Integer accountId, BigDecimal newBalance);
    boolean esCuentaDeUsuario(Integer accountNumber, String username);

}
