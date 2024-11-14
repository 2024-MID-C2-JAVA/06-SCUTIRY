package co.sofka.appservice.transaction;

import co.sofka.Transaction;
import co.sofka.TransactionRepository;

import java.util.List;


public class GetTransactionByIdUseCase {

    private final TransactionRepository transactionRepository;

    public GetTransactionByIdUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> apply(Transaction transaction) {
        return transactionRepository.getTransaction(transaction);
    }
}
