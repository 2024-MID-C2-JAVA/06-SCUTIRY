package co.sofka.appservice.transaction.strategy;

import co.sofka.Transaction;

import java.math.BigDecimal;


public class Withdraw implements TypeTransaction {
    @Override
    public Transaction movement(Transaction transaction) {
        transaction.setAmount(transaction.getAmount().subtract(transaction.getAmount().add(new BigDecimal(1))));
        transaction.setAmountCost(new BigDecimal(1));
        return transaction;
    }
}
