package co.sofka.appservice.transaction.strategy;

import co.sofka.Transaction;

public interface TypeTransaction {
    Transaction movement(Transaction transaction);
}
