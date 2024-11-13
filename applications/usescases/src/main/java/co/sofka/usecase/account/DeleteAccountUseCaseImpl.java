package co.sofka.usecase.account;

import co.sofka.Account;
import co.sofka.in.account.DeleteAccountUseCase;
import co.sofka.out.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteAccountUseCaseImpl implements DeleteAccountUseCase {

    private final AccountRepository accountRepository;

    public DeleteAccountUseCaseImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void apply(Account account) {
        accountRepository.deleteAccount(account);
    }
}
