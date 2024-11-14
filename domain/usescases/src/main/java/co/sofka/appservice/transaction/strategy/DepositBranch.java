package co.sofka.appservice.transaction.strategy;

import co.sofka.Transaction;

import java.math.BigDecimal;


public class DepositBranch implements TypeTransaction {
    @Override
    public Transaction movement(Transaction transaction) {
        transaction.setAmountCost(new BigDecimal(0));
        return transaction;
    }
}
