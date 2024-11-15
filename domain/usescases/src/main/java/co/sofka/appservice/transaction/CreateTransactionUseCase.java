package co.sofka.appservice.transaction;

import co.sofka.Transaction;
import co.sofka.out.TransactionRepository;
import co.sofka.appservice.transaction.strategy.AccountMovementContext;
import co.sofka.rabbitMq.SuccessBus;


public class CreateTransactionUseCase {
    private final SuccessBus bus;
    private final TransactionRepository transactionRepository;


    public CreateTransactionUseCase(SuccessBus bus, TransactionRepository transactionRepository) {
        this.bus = bus;
        this.transactionRepository = transactionRepository;
    }

    public Transaction apply(Transaction transaction) {
        Transaction transaction1=AccountMovementContext.accountMovement(transaction).movement(transaction);
        transactionRepository.createTransaction(transaction1);
        bus.sendSuccessMessage("SUCCESS: The transaction has been created");
        return transaction1;
    }

}
