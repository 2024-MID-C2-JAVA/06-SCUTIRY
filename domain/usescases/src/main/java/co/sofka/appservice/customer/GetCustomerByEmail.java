package co.sofka.appservice.customer;

import co.sofka.Customer;
import co.sofka.out.CustomerRepository;

public class GetCustomerByEmail {

    private final CustomerRepository customerRepository;

    public GetCustomerByEmail(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerByEmail(Customer customer) {
        return customerRepository.getCustomerByEmail(customer);
    }
}
