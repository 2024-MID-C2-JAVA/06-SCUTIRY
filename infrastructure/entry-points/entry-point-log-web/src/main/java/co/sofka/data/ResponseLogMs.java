package co.sofka.data;

import din.DinError;
import din.DinHeader;
import din.ResponseMs;

import java.util.List;

public class ResponseLogMs extends ResponseMs<LogDto> {

    public ResponseLogMs(DinHeader dinHeader, List<LogDto> transactionDtos, DinError dinError) {
        super(dinHeader != null ? dinHeader : new DinHeader(), transactionDtos, dinError != null ? dinError : defaultDinError());
    }

    private static DinError defaultDinError() {
        return new DinError();
    }
}
