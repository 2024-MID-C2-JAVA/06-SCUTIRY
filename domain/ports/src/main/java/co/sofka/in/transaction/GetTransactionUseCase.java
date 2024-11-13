package co.sofka.in.transaction;

import co.sofka.Transaction;

import java.util.List;

public interface GetTransactionUseCase {
    List<Transaction> apply(Transaction transaction);
}
