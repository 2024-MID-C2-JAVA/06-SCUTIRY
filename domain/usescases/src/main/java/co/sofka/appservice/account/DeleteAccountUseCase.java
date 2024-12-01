package co.sofka.appservice.account;

import co.sofka.Account;
import co.sofka.out.AccountRepository;
import co.sofka.rabbitMq.SuccessBus;


public class DeleteAccountUseCase {
    private final SuccessBus bus;
    private final AccountRepository accountRepository;

    public DeleteAccountUseCase(SuccessBus bus, AccountRepository accountRepository) {
        this.bus = bus;
        this.accountRepository = accountRepository;
    }

    public void apply(Account account) {
        bus.sendSuccessMessage("SUCCESS: The account has been deleted");
        accountRepository.deleteAccount(account);
    }
}
