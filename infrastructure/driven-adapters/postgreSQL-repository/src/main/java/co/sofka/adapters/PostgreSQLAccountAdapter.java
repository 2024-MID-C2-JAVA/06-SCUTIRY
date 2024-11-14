package co.sofka.adapters;

import co.sofka.Account;
import co.sofka.config.PostgreSQLAccountRepository;
import co.sofka.config.PostgreSQLCustomerRepository;
import co.sofka.data.AccountEntity;
import co.sofka.data.CustomerEntity;
import co.sofka.exception.AccountNumberException;
import co.sofka.exception.InvalidAmountException;
import co.sofka.exception.NotFoundException;
import co.sofka.AccountRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public class PostgreSQLAccountAdapter implements AccountRepository {

    private final PostgreSQLAccountRepository repository;
    private final PostgreSQLCustomerRepository customerRepository;

    public PostgreSQLAccountAdapter(PostgreSQLAccountRepository repository, PostgreSQLCustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void createAccount(Account account) {
        AccountEntity entity = new AccountEntity();
        CustomerEntity customer = new CustomerEntity();
        customer.setId(Integer.parseInt(account.getCustomerId()));
        entity.setNumber(account.getNumber());
        if(entity.getNumber() <=0){
            throw new AccountNumberException("The account number cant´t be 0 or negative");
        }
        entity.setAmount(account.getAmount());
        if(entity.getAmount().compareTo(BigDecimal.ZERO) < 0 ){
            throw new InvalidAmountException("The amount can´t be negative");
        }
        entity.setCustomer(customer);
        entity.setDeleted(false);
        entity.setCreatedAt(LocalDate.now());
        repository.save(entity);
    }


    @Override
    public void deleteAccount(Account account) {
        AccountEntity entity = repository.findById(Integer.parseInt(account.getId())).get();
        entity.setDeleted(true);
        repository.save(entity);
    }


    @Override
    public Account getAccount(Account account) {

        Optional<AccountEntity> entity = repository.findById(Integer.parseInt(account.getId()));

        if (entity.isEmpty()) {
            throw new NotFoundException("Account does not exists");
        }

        return new Account(
                String.valueOf(entity.get().getId()),
                entity.get().getNumber(),
                entity.get().getAmount(),
                String.valueOf(entity.get().getCustomerId()),
                entity.get().getCreatedAt());
    }

    @Override
    public void updateAccount(Account account) {
        AccountEntity entity = repository.getReferenceById(Integer.parseInt(account.getId()));
        CustomerEntity customer = customerRepository.getReferenceById(Integer.parseInt(account.getCustomerId()));
        entity.setAmount(account.getAmount());
        entity.setCustomer(customer);
        repository.save(entity);
    }

}
