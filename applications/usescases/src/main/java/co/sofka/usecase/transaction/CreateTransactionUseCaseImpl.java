package co.sofka.usecase.transaction;

import co.sofka.Transaction;
import co.sofka.exceptions.InvalidCreationException;
import co.sofka.in.transaction.CreateTransactionUseCase;
import co.sofka.out.TransactionRepository;
import co.sofka.usecase.transaction.strategy.AccountMovementContext;
import org.springframework.stereotype.Service;

@Service
public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final TransactionRepository transactionRepository;


    public CreateTransactionUseCaseImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction apply(Transaction transaction) {

        if(transaction == null){
            throw new InvalidCreationException("Transaction cannot be null");
        }

        Transaction transaction1=AccountMovementContext.accountMovement(transaction).movement(transaction);
        transactionRepository.createTransaction(transaction1);

        return transaction1;
    }

}
