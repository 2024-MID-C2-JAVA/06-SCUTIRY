package co.sofka.usecase.transaction.strategy;

import co.sofka.Transaction;

public interface TypeTransaction {
    Transaction movement(Transaction transaction);
}
