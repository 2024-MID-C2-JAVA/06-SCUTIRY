package co.sofka.appservice.account.strategy;

import co.sofka.Account;
import co.sofka.Transaction;

public interface AccountUpdateType {
    Account update(Transaction transaction,Account account);
}
