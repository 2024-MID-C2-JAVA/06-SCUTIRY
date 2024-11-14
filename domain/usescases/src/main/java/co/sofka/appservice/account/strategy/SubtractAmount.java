package co.sofka.appservice.account.strategy;

import co.sofka.Account;
import co.sofka.Transaction;
import co.sofka.enums.TypeOfTransaction;

import java.util.Set;

public class SubtractAmount implements AccountUpdateType{

    private static final Set<TypeOfTransaction> TRANSACTIONS_TO_DEDUCT = Set.of(
            TypeOfTransaction.ATM,
            TypeOfTransaction.ANOTHER_ACCOUNT,
            TypeOfTransaction.PHYSICAL_ESTABLISHMENT,
            TypeOfTransaction.WEB_PAGE,
            TypeOfTransaction.ATM_WITHDRAWAL
    );


    @Override
    public Account update(Transaction transaction, Account account) {
        if (TRANSACTIONS_TO_DEDUCT.contains(transaction.getType())) {
            account.setAmount(account.getAmount().subtract(transaction.getAmount()));
        }
        return account;
    }
}
