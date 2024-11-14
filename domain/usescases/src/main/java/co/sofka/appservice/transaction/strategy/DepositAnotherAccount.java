package co.sofka.appservice.transaction.strategy;

import co.sofka.Transaction;

import java.math.BigDecimal;

public class DepositAnotherAccount implements TypeTransaction {
    @Override
    public Transaction movement(Transaction transaction) {
        transaction.setAmount(transaction.getAmount().subtract(new BigDecimal("1.5")));
        transaction.setAmountCost(new BigDecimal("1.5"));
        return transaction;
    }
}
