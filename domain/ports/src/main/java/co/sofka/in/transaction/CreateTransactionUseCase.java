package co.sofka.in.transaction;

import co.sofka.Transaction;

public interface CreateTransactionUseCase {
    Transaction apply(Transaction transaction);
}
