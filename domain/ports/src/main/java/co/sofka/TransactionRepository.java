package co.sofka;

import java.util.List;

public interface TransactionRepository {
    void createTransaction(Transaction transaction);
    List<Transaction> getTransaction(Transaction transaction);
}
