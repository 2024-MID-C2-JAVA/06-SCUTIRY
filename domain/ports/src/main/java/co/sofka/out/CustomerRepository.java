package co.sofka.out;

import co.sofka.Customer;

public interface CustomerRepository {
    Customer createCustomer(Customer customer, String token);
    void deleteCustomer(Customer customer);
    Customer getCustomer(Customer customer);
    Customer getCustomerByEmail(Customer customer);
}
