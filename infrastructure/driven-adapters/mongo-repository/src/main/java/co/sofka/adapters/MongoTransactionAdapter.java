package co.sofka.adapters;

import co.sofka.Transaction;
import co.sofka.data.TransactionDocument;
import co.sofka.data.UserDocument;
import co.sofka.exception.GetNotFoundException;
import co.sofka.TransactionRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MongoTransactionAdapter implements TransactionRepository {

    private final MongoTemplate mongoTemplate;

    public MongoTransactionAdapter(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void createTransaction(Transaction transaction) {
        Optional<UserDocument> userDocument = Optional.ofNullable(mongoTemplate.findById(transaction.getAccountId(), UserDocument.class));

        if(userDocument.isEmpty()) {
            throw new GetNotFoundException("Account does not exist");
        }

        TransactionDocument transactionDocument = new TransactionDocument();
        transactionDocument.setAmount(transaction.getAmount());
        transactionDocument.setAmountCost(transaction.getAmountCost());
        transactionDocument.setTypeOfTransaction(transaction.getType());

        userDocument.get().getCustomer().getAccount().getTransactions().add(transactionDocument);

        mongoTemplate.save(userDocument.get());
    }

    @Override
    public List<Transaction> getTransaction(Transaction transaction) {

        Query query = new Query(Criteria.where("_id").is(transaction.getId())
                .and("customer.account_customer.is_deleted").is(false));


        UserDocument userDocument = mongoTemplate.findOne(query, UserDocument.class);


        if (userDocument == null || userDocument.getCustomer() == null ||
                userDocument.getCustomer().getAccount() == null ||
                userDocument.getCustomer().getAccount().getTransactions().isEmpty()) {
            System.out.println("Lista vacía");
            throw new GetNotFoundException("No transactions found for the given user ID");
        }


        List<Transaction> transactionList = userDocument.getCustomer()
                .getAccount()
                .getTransactions()
                .stream()
                .map(transactionDocument -> {
                    ZoneOffset zoneOffset = ZoneOffset.UTC;
                    OffsetDateTime offsetDateTime = transactionDocument.getTimeStamp().atOffset(zoneOffset);

                    return new Transaction(
                            transactionDocument.getId(),
                            transactionDocument.getAmount(),
                            transactionDocument.getAmountCost(),
                            transactionDocument.getTypeOfTransaction(),
                            offsetDateTime
                    );
                })
                .collect(Collectors.toList());
        
        for (Transaction t : transactionList) {
            System.out.println(t);
        }

        return transactionList;
    }



}
