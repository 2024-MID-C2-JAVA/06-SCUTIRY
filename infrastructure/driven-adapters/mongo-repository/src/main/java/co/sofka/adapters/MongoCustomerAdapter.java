package co.sofka.adapters;

import co.sofka.Customer;
import co.sofka.config.JwtService;
import co.sofka.data.CustomerDocument;
import co.sofka.data.UserDocument;
import co.sofka.exception.GetNotFoundException;
import co.sofka.out.CustomerRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class MongoCustomerAdapter implements CustomerRepository {

    private final MongoTemplate mongoTemplate;
    private final JwtService jwtService;

    public MongoCustomerAdapter(MongoTemplate mongoTemplate, JwtService jwtService) {
        this.mongoTemplate = mongoTemplate;
        this.jwtService = jwtService;
    }

    @Override
    public void createCustomer(Customer customer,String token) {
        CustomerDocument customerDocument = new CustomerDocument();
        customerDocument.setName(customer.getName());
        customerDocument.setDeleted(false);
        customerDocument.setCreatedAt(LocalDate.now());

        String email=jwtService.extractUsername(token);

        Query query = new Query(Criteria.where("email").is(email));

        UserDocument user = mongoTemplate.findOne(query, UserDocument.class);

        assert user != null;
        user.setCustomer(customerDocument);

        mongoTemplate.save(user);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        CustomerDocument customerDocument = mongoTemplate.findById(customer.getId(), CustomerDocument.class);
        if(customerDocument != null) {
            customerDocument.setDeleted(true);
            mongoTemplate.save(customerDocument);
        }
    }

    @Override
    public Customer getCustomer(Customer customer) {
        Optional<CustomerDocument>customerDocument= Optional.ofNullable(mongoTemplate.findById(customer.getId(), CustomerDocument.class));

        if(customerDocument.isEmpty()) {
            throw new GetNotFoundException("Customer does not exist");
        }

        return new Customer(
                customerDocument.get().getId(),
                customerDocument.get().getName(),
                customerDocument.get().getCreatedAt()
        );
    }

}
