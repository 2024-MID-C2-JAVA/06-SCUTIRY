package co.sofka.config;

import co.sofka.data.AccountTransactionEntity;
import co.sofka.data.AccountTransactionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgreSQLAccountTransactionRepository extends JpaRepository<AccountTransactionEntity, AccountTransactionId> {
}
