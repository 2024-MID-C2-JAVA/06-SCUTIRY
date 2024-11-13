package co.sofka.data.customer;


import din.DinError;
import din.DinHeader;
import din.ResponseMs;

public class ResponseCustomerMs extends ResponseMs<CustomerDto> {


    public ResponseCustomerMs(CustomerDto customerDto) {
        super(new DinHeader(), customerDto, defaultDinError());
    }


    public ResponseCustomerMs(DinHeader dinHeader, CustomerDto customerDto) {
        super(dinHeader != null ? dinHeader : new DinHeader(), customerDto, defaultDinError());
    }


    public ResponseCustomerMs(DinError dinError) {
        super(new DinHeader(), (CustomerDto) null, dinError != null ? dinError : defaultDinError());
    }

    public ResponseCustomerMs(CustomerDto customerDto, DinError dinError) {
        super(new DinHeader(), customerDto, dinError != null ? dinError : defaultDinError());
    }

    public ResponseCustomerMs(DinHeader dinHeader, CustomerDto customerDto, DinError dinError) {
        super(dinHeader != null ? dinHeader : new DinHeader(), customerDto, dinError != null ? dinError : defaultDinError());
    }

    private static DinError defaultDinError() {
        return new DinError();
    }
}
