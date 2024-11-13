package co.sofka.handler;

import co.sofka.Account;
import co.sofka.Transaction;
import co.sofka.data.transaction.TransactionDto;
import co.sofka.usecase.account.GetAccountByIdUseCaseImpl;
import co.sofka.usecase.account.UpdateAccountUseCaseImpl;
import co.sofka.usecase.transaction.CreateTransactionUseCaseImpl;
import co.sofka.usecase.transaction.GetTransactionByIdUseCaseImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionHandler {

    private final CreateTransactionUseCaseImpl transactionUseCase;
    private final GetTransactionByIdUseCaseImpl transactionByIdUseCase;
    private final UpdateAccountUseCaseImpl updateAccountUseCase;
    private final GetAccountByIdUseCaseImpl getAccountByIdUseCase;

    public TransactionHandler(CreateTransactionUseCaseImpl transactionUseCase, GetTransactionByIdUseCaseImpl transactionByIdUseCase, UpdateAccountUseCaseImpl updateAccountUseCase, GetAccountByIdUseCaseImpl getAccountByIdUseCase) {
        this.transactionUseCase = transactionUseCase;
        this.transactionByIdUseCase = transactionByIdUseCase;
        this.updateAccountUseCase = updateAccountUseCase;
        this.getAccountByIdUseCase = getAccountByIdUseCase;
    }

    public void createTransaction(TransactionDto transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setType(transactionDTO.getType());
        transaction.setAccountId(transactionDTO.getAccountId());
        Account account=new Account();
        account.setId(transactionDTO.getAccountId());
        Account account2=getAccountByIdUseCase.apply(account);
        transaction=transactionUseCase.apply(transaction);
        System.out.println("TRANSACCION: "+transaction);
        updateAccountUseCase.updateAccount(account2,transaction);
    }


    public List<TransactionDto> getTransactionsByUserId(TransactionDto getTransactionDTO) {

        List<Transaction> transactions = transactionByIdUseCase.apply(new Transaction(getTransactionDTO.getId()));

        return transactions.stream()
                .map(transaction -> new TransactionDto(
                        transaction.getId(),
                        transaction.getAccountId(),
                        transaction.getAmount(),
                        transaction.getAmountCost(),
                        transaction.getType(),
                        transaction.getTimeStamp()
                ))
                .collect(Collectors.toList());
    }

}


