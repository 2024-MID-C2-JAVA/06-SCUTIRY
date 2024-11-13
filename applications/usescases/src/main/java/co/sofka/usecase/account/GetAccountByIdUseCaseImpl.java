package co.sofka.usecase.account;

import co.sofka.Account;
import co.sofka.in.account.GetAccountByIdUseCase;
import co.sofka.out.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class GetAccountByIdUseCaseImpl implements GetAccountByIdUseCase {

    private final AccountRepository accountRepository;

    public GetAccountByIdUseCaseImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account apply(Account account) {
        return accountRepository.getAccount(account);
    }
}
