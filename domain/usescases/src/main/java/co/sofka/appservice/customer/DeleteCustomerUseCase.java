package co.sofka.appservice.customer;

import co.sofka.Customer;
import co.sofka.out.CustomerRepository;
import co.sofka.rabbitMq.SuccessBus;


public class DeleteCustomerUseCase {

    private final SuccessBus bus;
    private final CustomerRepository customerRepository;

    public DeleteCustomerUseCase(SuccessBus bus, CustomerRepository customerRepository) {
        this.bus = bus;
        this.customerRepository = customerRepository;
    }

    public void apply(Customer customer) {
        bus.sendSuccessMessage("SUCCESS:The customer has been deleted");
        customerRepository.deleteCustomer(customer);
    }
}
