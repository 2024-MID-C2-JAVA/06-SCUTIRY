package co.sofka.appservice.account;

import co.sofka.Account;
import co.sofka.exceptions.InvalidCreationException;
import co.sofka.exceptions.InvalidFundsException;
import co.sofka.exceptions.InvalidNumberException;
import co.sofka.AccountRepository;
import co.sofka.rabbitMq.Bus;

import java.math.BigDecimal;


public class CreateAccountUseCase {

    private final Bus bus;
    private final AccountRepository accountRepository;

    public CreateAccountUseCase(Bus bus, AccountRepository accountRepository) {
        this.bus = bus;
        this.accountRepository = accountRepository;
    }

    public void apply(Account account) {
        if (account == null) {
            bus.sendMessage("ERROR: The account had null valor",false);
            throw new InvalidCreationException("The account cannot be null");
        }

        if (account.getAmount() == null || account.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            bus.sendMessage("ERROR: The amount of the account was negative or null",false);
            throw new InvalidFundsException("The amount cannot be negative or null: " + account.getAmount());
        }

        if (account.getNumber() <= 0) {
            bus.sendMessage("ERROR: The account number was 0",false);
            throw new InvalidNumberException("The account number must be a positive integer: " + account.getNumber());
        }

        bus.sendMessage("SUCCESS: The account with number "+account.getNumber()+" was created",true);

        accountRepository.createAccount(account);
    }

}
