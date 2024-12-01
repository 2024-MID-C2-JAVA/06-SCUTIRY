package co.sofka.in.account;

import co.sofka.Account;
import co.sofka.Transaction;

public interface UpdateAccountUseCase {
    void updateAccount(Account account, Transaction transaction);
}
