package co.sofka;

import co.sofka.data.customer.*;
import co.sofka.handler.CustomerHandler;
import din.DinError;
import din.DinErrorEnum;
import din.RequestMs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

    private final CustomerHandler customerHandler;

    public CustomerRestController(CustomerHandler customerHandler) {
        this.customerHandler = customerHandler;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseCustomerMs> createCustomer(@RequestBody RequestMs<CustomerDto> customerDTO,
                                                             @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            System.out.println("TOKEN: " + token);

            customerHandler.createCustomer(customerDTO.getDinBody(), token);

            return ResponseEntity.ok(new ResponseCustomerMs(customerDTO.getDinHeader(),
                    customerDTO.getDinBody(),
                    new DinError(DinErrorEnum.CUSTOMER_CREATED)));
        } catch (Exception e) {
            DinError error = new DinError(DinErrorEnum.CREATION_ERROR);
            error.setMessage("Error creating customer: " + e.getMessage());
            ResponseCustomerMs responseWithError = new ResponseCustomerMs(customerDTO.getDinHeader(),
                    customerDTO.getDinBody(),
                    error);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseWithError);
        }
    }


    @PostMapping("/delete")
    public ResponseEntity<ResponseCustomerMs> deleteCustomer(@RequestBody RequestMs<CustomerDto> deleteCustomerDTO) {
        try {
            customerHandler.deleteCustomer(deleteCustomerDTO.getDinBody());
            return ResponseEntity.ok(new ResponseCustomerMs(deleteCustomerDTO.getDinHeader(), deleteCustomerDTO.getDinBody(), new DinError(DinErrorEnum.CUSTOMER_DELETED)));
        } catch (Exception e) {
            ResponseCustomerMs responseWithError = new ResponseCustomerMs(new DinError(DinErrorEnum.DELETE_ERROR));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseWithError);
        }
    }

    @PostMapping("/get")
    public ResponseEntity<ResponseCustomerMs> getCustomerById(@RequestBody RequestMs<CustomerDto> dto) {
        try {
            dto.setDinBody(customerHandler.getCustomerById(dto.getDinBody()));
            return ResponseEntity.ok(new ResponseCustomerMs(dto.getDinHeader(), dto.getDinBody(), new DinError(DinErrorEnum.SUCCESS)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseCustomerMs(dto.getDinHeader(), dto.getDinBody(), new DinError(DinErrorEnum.CUSTOMER_NOT_FOUND)));
        }
    }

}
