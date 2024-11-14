package co.sofka.appservice.transaction.strategy;

import co.sofka.Transaction;

import java.math.BigDecimal;


public class DepositATM implements TypeTransaction {
    @Override
    public Transaction movement(Transaction transaction) {
        transaction.setAmount(transaction.getAmount().add(new BigDecimal(2)));
        transaction.setAmountCost(new BigDecimal(2));
        return transaction;
    }
}
