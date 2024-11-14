package co.sofka.appservice.account;

import co.sofka.Account;
import co.sofka.Transaction;
import co.sofka.AccountRepository;
import co.sofka.appservice.account.strategy.AccountUpdateContext;
import co.sofka.rabbitMq.Bus;


public class UpdateAccountUseCase {
    private final Bus bus;
    private final AccountRepository accountRepository;

    public UpdateAccountUseCase(Bus bus, AccountRepository accountRepository) {
        this.bus = bus;
        this.accountRepository = accountRepository;
    }


    public void apply(Account account, Transaction transaction) {
        Account account1= AccountUpdateContext.accountUpdate(transaction).update(transaction,account);
        accountRepository.updateAccount(account1);
    }

}
