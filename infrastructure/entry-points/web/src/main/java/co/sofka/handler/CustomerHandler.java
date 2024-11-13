package co.sofka.handler;

import co.sofka.Customer;
import co.sofka.data.customer.CustomerDto;
import co.sofka.exceptions.InvalidNameCustomerException;
import co.sofka.usecase.customer.CreateCustomerUseCaseImpl;
import co.sofka.usecase.customer.DeleteCustomerUseCaseImpl;
import co.sofka.usecase.customer.GetCustomerByIdUseCaseImpl;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerHandler {

    private final CreateCustomerUseCaseImpl createCustomerUseCaseImpl;
    private final DeleteCustomerUseCaseImpl deleteCustomerUseCaseImpl;
    private final GetCustomerByIdUseCaseImpl getCustomerByIdUseCaseImpl;

    public CustomerHandler(CreateCustomerUseCaseImpl createCustomerUseCaseImpl, DeleteCustomerUseCaseImpl deleteCustomerUseCaseImpl, GetCustomerByIdUseCaseImpl getCustomerByIdUseCaseImpl) {
        this.createCustomerUseCaseImpl = createCustomerUseCaseImpl;
        this.deleteCustomerUseCaseImpl = deleteCustomerUseCaseImpl;
        this.getCustomerByIdUseCaseImpl = getCustomerByIdUseCaseImpl;
    }

    public void createCustomer(CustomerDto customerDto,String authorizationHeader) {
        try {
            System.out.println("Header: "+authorizationHeader);
            Customer customer = new Customer();
            customer.setName(customerDto.getName());
            createCustomerUseCaseImpl.apply(customer,authorizationHeader);
        } catch (InvalidNameCustomerException e) {
            throw new InvalidNameCustomerException(e.getMessage());
        }
    }

    public void deleteCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        deleteCustomerUseCaseImpl.apply(customer);
    }

    public CustomerDto getCustomerById(CustomerDto getCustomerByIdDTO) {

        Optional<Customer> customer = Optional.ofNullable(getCustomerByIdUseCaseImpl.apply(new Customer(getCustomerByIdDTO.getId())));

        return new CustomerDto(
                customer.get().getId(),
                customer.get().getName(),
                customer.get().getCreatedAt()
        );
    }

}
