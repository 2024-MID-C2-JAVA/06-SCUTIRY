package co.sofka.handler;

import co.sofka.Customer;
import co.sofka.data.customer.CustomerDto;
import co.sofka.exceptions.InvalidNameCustomerException;
import co.sofka.appservice.customer.CreateCustomerUseCase;
import co.sofka.appservice.customer.DeleteCustomerUseCase;
import co.sofka.appservice.customer.GetCustomerByIdUseCase;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerHandler {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;

    public CustomerHandler(CreateCustomerUseCase createCustomerUseCase, DeleteCustomerUseCase deleteCustomerUseCase, GetCustomerByIdUseCase getCustomerByIdUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
        this.getCustomerByIdUseCase = getCustomerByIdUseCase;
    }

    public void createCustomer(CustomerDto customerDto,String authorizationHeader) {
        try {
            System.out.println("Header: "+authorizationHeader);
            Customer customer = new Customer();
            customer.setName(customerDto.getName());
            createCustomerUseCase.apply(customer,authorizationHeader);
        } catch (InvalidNameCustomerException e) {
            throw new InvalidNameCustomerException(e.getMessage());
        }
    }

    public void deleteCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        deleteCustomerUseCase.apply(customer);
    }

    public CustomerDto getCustomerById(CustomerDto getCustomerByIdDTO) {

        Optional<Customer> customer = Optional.ofNullable(getCustomerByIdUseCase.apply(new Customer(getCustomerByIdDTO.getId())));

        return new CustomerDto(
                customer.get().getId(),
                customer.get().getName(),
                customer.get().getCreatedAt()
        );
    }

}
