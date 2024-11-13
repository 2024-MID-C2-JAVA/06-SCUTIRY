package co.sofka.usecase.transaction;

import co.sofka.Transaction;
import co.sofka.in.transaction.GetTransactionUseCase;
import co.sofka.out.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTransactionByIdUseCaseImpl implements GetTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public GetTransactionByIdUseCaseImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> apply(Transaction transaction) {
        return transactionRepository.getTransaction(transaction);
    }
}
