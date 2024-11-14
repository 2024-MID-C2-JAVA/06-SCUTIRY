package co.sofka.appservice.customer;

import co.sofka.Customer;
import co.sofka.exceptions.InvalidCreationException;
import co.sofka.exceptions.InvalidNameCustomerException;
import co.sofka.CustomerRepository;


public class CreateCustomerUseCase {

    private final CustomerRepository customerRepository;

    public CreateCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void apply(Customer customer,String token) {

        if(customer == null) {
            throw new InvalidCreationException("Customer cannot be null");
        }

        if(customer.getName().equals(" ") || customer.getName() == null || customer.getName().isBlank()){
            throw new InvalidNameCustomerException("The customer name must not be empty or null");
        }

        customerRepository.createCustomer(customer,token);
    }
}
