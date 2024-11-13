package co.sofka.data.account;


import din.DinError;
import din.DinHeader;
import din.ResponseMs;

public class ResponseAccountMs extends ResponseMs<AccountDto> {

    public ResponseAccountMs(AccountDto accountDto) {
        super(new DinHeader(), accountDto, defaultDinError());
    }


    public ResponseAccountMs(DinHeader dinHeader, AccountDto accountDto) {
        super(dinHeader != null ? dinHeader : new DinHeader(), accountDto, defaultDinError());
    }

    public ResponseAccountMs(DinError dinError) {
        super(new DinHeader(), (AccountDto) null, dinError != null ? dinError : defaultDinError());
    }

    public ResponseAccountMs(AccountDto accountDto, DinError dinError) {
        super(new DinHeader(), accountDto, dinError != null ? dinError : defaultDinError());
    }

    public ResponseAccountMs(DinHeader dinHeader, AccountDto accountDto, DinError dinError) {
        super(dinHeader != null ? dinHeader : new DinHeader(), accountDto, dinError != null ? dinError : defaultDinError());
    }

    private static DinError defaultDinError() {
        return new DinError();
    }
}
