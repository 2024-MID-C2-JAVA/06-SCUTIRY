package co.sofka.appservice.customer;

import co.sofka.Customer;
import co.sofka.CustomerRepository;


public class GetCustomerByIdUseCase {

    private final CustomerRepository customerRepository;


    public GetCustomerByIdUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer apply(Customer customer) {
        return customerRepository.getCustomer(customer);
    }
}
