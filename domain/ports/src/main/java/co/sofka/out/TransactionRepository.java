package co.sofka.out;

import co.sofka.Transaction;

import java.util.List;

public interface TransactionRepository {
    void createTransaction(Transaction transaction);
    List<Transaction> getTransaction(Transaction transaction);
}
