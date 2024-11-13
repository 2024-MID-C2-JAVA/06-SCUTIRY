package co.sofka.config;

import co.sofka.data.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgreSQLAccountRepository extends JpaRepository<AccountEntity,Integer> {
}
