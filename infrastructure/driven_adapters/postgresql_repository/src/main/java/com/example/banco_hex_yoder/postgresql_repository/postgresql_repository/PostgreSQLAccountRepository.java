package com.example.banco_hex_yoder.postgresql_repository.postgresql_repository;

import com.example.banco_hex_yoder.model.Account;
import com.example.banco_hex_yoder.postgresql_repository.data.entidades.CuentaEntity;
import com.example.banco_hex_yoder.postgresql_repository.data.entidades.TransaccionEntity;
import com.example.banco_hex_yoder.postgresql_repository.data.entidades.TransactionAccountDetailEntity;
import com.example.banco_hex_yoder.postgresql_repository.data.compositekeys.TransactionAccountDetailId;
import com.example.banco_hex_yoder.postgresql_repository.data.repositorios.AccountRepository;
import com.example.banco_hex_yoder.postgresql_repository.data.repositorios.TransactionAccountDetailRepository;
import com.example.banco_hex_yoder.postgresql_repository.data.repositorios.TransactionRepository;
import com.example.banco_hex_yoder.postgresql_repository.data.mappers.EntityMapper;
import com.example.banco_hex_yoder.gateway.AccountGateway;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@ConditionalOnProperty(name = "app.useDB", havingValue = "0")
public class PostgreSQLAccountRepository implements AccountGateway {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionAccountDetailRepository transactionAccountDetailRepository;
    ;

    public PostgreSQLAccountRepository(AccountRepository accountRepository,
                                       TransactionRepository transactionRepository,
                                       TransactionAccountDetailRepository transactionAccountDetailRepository
                                      ) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.transactionAccountDetailRepository = transactionAccountDetailRepository;
;
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return accountRepository.findById(id).map(EntityMapper::toModel);
    }

    @Override
    public Optional<Account> findByNumber(Integer number) {
        return accountRepository.findByNumber(number).map(EntityMapper::toModel);
    }

    @Override
    public Account save(Account account) {
        CuentaEntity entity = EntityMapper.toEntity(account);
        return EntityMapper.toModel(accountRepository.save(entity));
    }

    @Override
    public void updateBalance(Integer accountId, BigDecimal newBalance) {
        accountRepository.findById(accountId).ifPresent(entity -> {
            entity.setAmount(newBalance);
            accountRepository.save(entity);
        });
    }

    @Override
    public void registrarTransaccion(BigDecimal monto, BigDecimal costo, String tipoTransaccion, Integer cuentaOrigenNumber, Integer cuentaDestinoNumber) {
        TransaccionEntity transaccion = new TransaccionEntity();
        transaccion.setAmountTransaction(monto);
        transaccion.setTransactionCost(costo);
        transaccion.setTypeTransaction(tipoTransaccion);
        transaccion.setTimeStamp(LocalDateTime.now());

        transaccion = transactionRepository.save(transaccion);

        CuentaEntity cuentaOrigen = accountRepository.findByNumber(cuentaOrigenNumber)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta origen no encontrada"));
        CuentaEntity cuentaDestino = accountRepository.findByNumber(cuentaDestinoNumber)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta destino no encontrada"));

        TransactionAccountDetailEntity detalleOrigen = new TransactionAccountDetailEntity();
        detalleOrigen.setId(new TransactionAccountDetailId(transaccion.getId(), cuentaOrigen.getId()));
        detalleOrigen.setTransaction(transaccion);
        detalleOrigen.setAccount(cuentaOrigen);
        detalleOrigen.setTransactionRole("ordenante");

        transactionAccountDetailRepository.save(detalleOrigen);

        TransactionAccountDetailEntity detalleDestino = new TransactionAccountDetailEntity();
        detalleDestino.setId(new TransactionAccountDetailId(transaccion.getId(), cuentaDestino.getId()));
        detalleDestino.setTransaction(transaccion);
        detalleDestino.setAccount(cuentaDestino);
        detalleDestino.setTransactionRole("beneficiario");

        transactionAccountDetailRepository.save(detalleDestino);
    }

    @Override
    public boolean esCuentaDeUsuario(Integer accountNumber, String username) {
        return accountRepository.findByNumber(accountNumber)
                .map(account -> account.getCustomer().getUsername().equals(username))
                .orElse(false);
    }


}
