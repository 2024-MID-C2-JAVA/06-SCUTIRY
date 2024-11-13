package co.sofka.usecase.account;

import co.sofka.Account;
import co.sofka.Transaction;
import co.sofka.in.account.UpdateAccountUseCase;
import co.sofka.out.AccountRepository;
import co.sofka.usecase.account.strategy.AccountUpdateContext;
import org.springframework.stereotype.Service;

@Service
public class UpdateAccountUseCaseImpl implements UpdateAccountUseCase {

    private final AccountRepository accountRepository;

    public UpdateAccountUseCaseImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void updateAccount(Account account, Transaction transaction) {
        Account account1= AccountUpdateContext.accountUpdate(transaction).update(transaction,account);
        System.out.println("ACCOUNT: "+account1);
        //accountRepository.updateAccount(account);
    }

}
