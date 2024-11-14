package co.sofka.appservice.transaction;

import co.sofka.Transaction;
import co.sofka.exceptions.InvalidCreationException;
import co.sofka.TransactionRepository;
import co.sofka.appservice.transaction.strategy.AccountMovementContext;
import co.sofka.rabbitMq.Bus;


public class CreateTransactionUseCase {
    private final Bus bus;
    private final TransactionRepository transactionRepository;


    public CreateTransactionUseCase(Bus bus, TransactionRepository transactionRepository) {
        this.bus = bus;
        this.transactionRepository = transactionRepository;
    }

    public Transaction apply(Transaction transaction) {
        Transaction transaction1=AccountMovementContext.accountMovement(transaction).movement(transaction);
        transactionRepository.createTransaction(transaction1);
        bus.sendMessage("SUCCESS: The transaction has been created",true);
        return transaction1;
    }

}
