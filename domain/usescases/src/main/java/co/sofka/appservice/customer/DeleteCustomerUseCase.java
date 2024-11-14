package co.sofka.appservice.customer;

import co.sofka.Customer;
import co.sofka.CustomerRepository;
import co.sofka.rabbitMq.Bus;


public class DeleteCustomerUseCase {

    private final Bus bus;
    private final CustomerRepository customerRepository;

    public DeleteCustomerUseCase(Bus bus, CustomerRepository customerRepository) {
        this.bus = bus;
        this.customerRepository = customerRepository;
    }

    public void apply(Customer customer) {
        bus.sendMessage("SUCCESS:The customer has been deleted",true);
        customerRepository.deleteCustomer(customer);
    }
}
