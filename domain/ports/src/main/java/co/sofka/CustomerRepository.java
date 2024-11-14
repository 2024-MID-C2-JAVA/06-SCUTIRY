package co.sofka;

public interface CustomerRepository {
    void createCustomer(Customer customer,String token);
    void deleteCustomer(Customer customer);
    Customer getCustomer(Customer customer);
}
