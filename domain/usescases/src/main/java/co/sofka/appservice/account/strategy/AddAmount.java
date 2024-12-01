package co.sofka.appservice.account.strategy;

import co.sofka.Account;
import co.sofka.Transaction;
import co.sofka.enums.TypeOfTransaction;

public class AddAmount implements AccountUpdateType{
    @Override
    public Account update(Transaction transaction, Account account) {
        if (transaction.getType().equals(TypeOfTransaction.BRANCH)) {
            account.setAmount(account.getAmount().add(transaction.getAmount()));
        }
        return account;
    }
}
