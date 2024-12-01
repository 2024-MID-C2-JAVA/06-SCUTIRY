package co.sofka.handler;

import co.sofka.Account;
import co.sofka.Transaction;
import co.sofka.data.transaction.TransactionDto;
import co.sofka.appservice.account.GetAccountByIdUseCase;
import co.sofka.appservice.account.UpdateAccountUseCase;
import co.sofka.appservice.transaction.CreateTransactionUseCase;
import co.sofka.appservice.transaction.GetTransactionByIdUseCase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionHandler {

    private final CreateTransactionUseCase transactionUseCase;
    private final GetTransactionByIdUseCase transactionByIdUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final GetAccountByIdUseCase getAccountByIdUseCase;;

    public TransactionHandler(CreateTransactionUseCase transactionUseCase, GetTransactionByIdUseCase transactionByIdUseCase, UpdateAccountUseCase updateAccountUseCase, GetAccountByIdUseCase getAccountByIdUseCase) {
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
        account2.setId(transactionDTO.getAccountId());
        updateAccountUseCase.apply(account2,transaction);
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


