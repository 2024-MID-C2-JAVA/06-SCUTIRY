package co.sofka.usecase.customer;

import co.sofka.Customer;
import co.sofka.in.customer.DeleteCustomerUseCase;
import co.sofka.out.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCase {

    private final CustomerRepository customerRepository;

    public DeleteCustomerUseCaseImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void apply(Customer customer) {
        customerRepository.deleteCustomer(customer);
    }
}
