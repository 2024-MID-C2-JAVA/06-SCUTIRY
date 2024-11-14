package co.sofka.adapters;

import co.sofka.Customer;
import co.sofka.config.PostgreSQLCustomerRepository;
import co.sofka.data.CustomerEntity;
import co.sofka.exception.NotFoundException;
import co.sofka.exceptions.InvalidNameCustomerException;
import co.sofka.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class PostgreSQLCustomerAdapter implements CustomerRepository {

    private final PostgreSQLCustomerRepository repository;

    public PostgreSQLCustomerAdapter(PostgreSQLCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createCustomer(Customer customer,String token) {
        CustomerEntity entity = new CustomerEntity();

        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new InvalidNameCustomerException("The customer name cannot be empty or blank");
        }

        entity.setName(customer.getName());
        entity.setCreatedAt(LocalDate.now());
        entity.setDeleted(false);
        repository.save(entity);
    }



    @Override
    public void deleteCustomer(Customer customer) {
        CustomerEntity entity=repository.findById(Integer.parseInt(customer.getId())).get();
        entity.setDeleted(true);
        repository.save(entity);
    }


    @Override
    public Customer getCustomer(Customer customer) {
        Optional<CustomerEntity> entity=repository.findById(Integer.parseInt(customer.getId()));

        if(entity.isEmpty()){
            throw new NotFoundException("Customer does not exists");
        }

        return new Customer(
                String.valueOf(entity.get().getId()),
                entity.get().getName(),
                entity.get().getCreatedAt()
        );
    }
}
