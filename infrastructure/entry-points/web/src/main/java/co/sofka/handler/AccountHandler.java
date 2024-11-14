package co.sofka.handler;

import co.sofka.Account;
import co.sofka.data.account.AccountDto;
import co.sofka.exceptions.InvalidCreationException;
import co.sofka.exceptions.InvalidFundsException;
import co.sofka.appservice.account.CreateAccountUseCase;
import co.sofka.appservice.account.DeleteAccountUseCase;
import co.sofka.appservice.account.GetAccountByIdUseCase;
import cryptography.AESUtilAdapter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AccountHandler {

    private final CreateAccountUseCase createAccountUseCase;
    private final GetAccountByIdUseCase getAccountByIdUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;


    public AccountHandler(CreateAccountUseCase createAccountUseCase, GetAccountByIdUseCase getAccountByIdUseCase, DeleteAccountUseCase deleteAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.getAccountByIdUseCase = getAccountByIdUseCase;
        this.deleteAccountUseCase = deleteAccountUseCase;
    }

    public void createAccount(AccountDto accountDTO) {
        try {
            Account account = new Account();
            account.setNumber(Integer.parseInt(accountDTO.getNumber()));
            account.setAmount(accountDTO.getAmount());
            account.setCustomerId(accountDTO.getCustomerId());
            account.setCreatedAt(LocalDate.now());
            createAccountUseCase.apply(account);
        } catch (InvalidCreationException e) {
            throw new InvalidCreationException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        } catch (InvalidFundsException e) {
            throw new InvalidFundsException(e.getMessage());
        }
    }

    public void deleteAccount(AccountDto accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        deleteAccountUseCase.apply(account);
    }

    public AccountDto getAccountById(AccountDto accountDTO) {
        AESUtilAdapter adapter = new AESUtilAdapter();
        System.out.println("Account number en getAccountById: " + accountDTO.getNumber());
        try {
            Account account = getAccountByIdUseCase.apply(new Account(Integer.parseInt(accountDTO.getNumber())));

            return new AccountDto(
                    String.valueOf(account.getNumber()),
                    account.getAmount(),
                    account.getCustomerId(),
                    account.getCreatedAt());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
