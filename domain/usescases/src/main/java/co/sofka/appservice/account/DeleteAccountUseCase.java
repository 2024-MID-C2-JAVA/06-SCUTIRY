package co.sofka.appservice.account;

import co.sofka.Account;
import co.sofka.AccountRepository;
import co.sofka.rabbitMq.Bus;


public class DeleteAccountUseCase {
    private final Bus bus;
    private final AccountRepository accountRepository;

    public DeleteAccountUseCase(Bus bus, AccountRepository accountRepository) {
        this.bus = bus;
        this.accountRepository = accountRepository;
    }

    public void apply(Account account) {
        bus.sendMessage("SUCCESS: The account has been deleted",true);
        accountRepository.deleteAccount(account);
    }
}
