package co.sofka.appservice.account;

import co.sofka.Account;
import co.sofka.exceptions.InvalidCreationException;
import co.sofka.exceptions.InvalidFundsException;
import co.sofka.exceptions.InvalidNumberException;
import co.sofka.out.AccountRepository;
import co.sofka.rabbitMq.ErrorBus;
import co.sofka.rabbitMq.SuccessBus;

import java.math.BigDecimal;


public class CreateAccountUseCase {

    private final SuccessBus bus;
    private final ErrorBus errorBus;
    private final AccountRepository accountRepository;

    public CreateAccountUseCase(SuccessBus bus, ErrorBus errorBus, AccountRepository accountRepository) {
        this.bus = bus;
        this.errorBus = errorBus;
        this.accountRepository = accountRepository;
    }

    public void apply(Account account) {
        if (account == null) {
            errorBus.sendErrorMessage("ERROR: The account had null valor");
            throw new InvalidCreationException("The account cannot be null");
        }

        if (account.getAmount() == null || account.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            errorBus.sendErrorMessage("ERROR: The amount of the account was negative or null");
            throw new InvalidFundsException("The amount cannot be negative or null: " + account.getAmount());
        }

        if (account.getNumber() <= 0) {
            errorBus.sendErrorMessage("ERROR: The account number was 0");
            throw new InvalidNumberException("The account number must be a positive integer: " + account.getNumber());
        }

        bus.sendSuccessMessage("SUCCESS: The account with number "+account.getNumber()+" was created");

        accountRepository.createAccount(account);
    }

}
