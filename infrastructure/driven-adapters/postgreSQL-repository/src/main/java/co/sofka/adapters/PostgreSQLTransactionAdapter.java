package co.sofka.adapters;

import co.sofka.Transaction;
import co.sofka.config.PostgreSQLAccountRepository;
import co.sofka.config.PostgreSQLAccountTransactionRepository;
import co.sofka.config.PostgreSQLTransactionRepository;
import co.sofka.data.AccountEntity;
import co.sofka.data.AccountTransactionEntity;
import co.sofka.data.TransactionEntity;
import co.sofka.exception.InvalidAmountException;
import co.sofka.exception.TransactionNotFoundException;
import co.sofka.TransactionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostgreSQLTransactionAdapter implements TransactionRepository {

    private final PostgreSQLTransactionRepository repository;
    private final PostgreSQLAccountRepository accountRepository;
    private final PostgreSQLAccountTransactionRepository accountTransactionRepository;

    public PostgreSQLTransactionAdapter(PostgreSQLTransactionRepository transactionRepository, PostgreSQLAccountRepository accountRepository, PostgreSQLAccountTransactionRepository accountTransactionRepository) {
        this.repository = transactionRepository;
        this.accountRepository = accountRepository;
        this.accountTransactionRepository = accountTransactionRepository;
    }

    @Override
    @Transactional
    public void createTransaction(Transaction transaction) {

        TransactionEntity transactionEntity = new TransactionEntity();
        if(transaction.getAmount().compareTo(BigDecimal.ZERO) < 0 ){
            throw new InvalidAmountException("The amount can´t be negative");
        }
        transactionEntity.setAmount(transaction.getAmount());
        transactionEntity.setAmountCost(transaction.getAmountCost());
        transactionEntity.setType(transaction.getType());

        AccountEntity accountEntity = accountRepository.findById(Integer.parseInt(transaction.getAccountId())).get();

        AccountTransactionEntity accountTransaction = new AccountTransactionEntity();
        accountTransaction.setAccount(accountEntity);
        accountTransaction.setTransaction(transactionEntity);


        accountEntity.addTransaction(accountTransaction);
        transactionEntity.addAccount(accountTransaction);

        repository.save(transactionEntity);
        accountTransactionRepository.save(accountTransaction);
    }

    @Override
    public List<Transaction> getTransaction(Transaction transaction) {

        List<TransactionEntity> transactionEntities = List.of(repository.findById(Integer.parseInt(transaction.getId())).get());

        if (transactionEntities.isEmpty()) {
            throw new TransactionNotFoundException("No transactions found for the given account");
        }

        List<Transaction> transactions = transactionEntities.stream()
                .map(transactionEntity -> new Transaction(
                        String.valueOf(transactionEntity.getId()),
                        transactionEntity.getAmount(),
                        transactionEntity.getAmountCost(),
                        transactionEntity.getType(),
                        transactionEntity.getTimestamp()
                ))
                .collect(Collectors.toList());

        return transactions;
    }


}
