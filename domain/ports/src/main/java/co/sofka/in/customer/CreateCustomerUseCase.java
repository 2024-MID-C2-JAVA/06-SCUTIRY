package co.sofka.in.customer;

import co.sofka.Customer;

public interface CreateCustomerUseCase {
    void apply(Customer customer,String token);
}
