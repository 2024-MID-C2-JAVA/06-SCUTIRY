package co.sofka.usecase.customer;

import co.sofka.Customer;
import co.sofka.in.customer.GetCusomterByIdUseCase;
import co.sofka.out.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class GetCustomerByIdUseCaseImpl implements GetCusomterByIdUseCase {

    private final CustomerRepository customerRepository;


    public GetCustomerByIdUseCaseImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer apply(Customer customer) {
        return customerRepository.getCustomer(customer);
    }
}
