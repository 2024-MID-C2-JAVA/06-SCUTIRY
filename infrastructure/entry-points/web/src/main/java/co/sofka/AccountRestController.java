package co.sofka;

import co.sofka.data.account.AccountDto;
import co.sofka.data.account.ResponseAccountMs;
import co.sofka.handler.AccountHandler;
import cryptography.AESUtilAdapter;
import din.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/account")
public class AccountRestController {

    private final AccountHandler accountHandler;

    public AccountRestController(AccountHandler accountHandler) {
        this.accountHandler = accountHandler;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseAccountMs>createAccount(@RequestBody RequestMs<AccountDto> dto) {
        try{
            accountHandler.createAccount(dto.getDinBody());
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseAccountMs(dto.getDinHeader(), dto.getDinBody(),new DinError(DinErrorEnum.ACCOUNT_CREATED)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseAccountMs(dto.getDinHeader(), dto.getDinBody(),new DinError(DinErrorEnum.CREATION_ERROR)));
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseAccountMs>deleteAccount(@RequestBody RequestMs<AccountDto> dto) {
        try{
            accountHandler.deleteAccount(dto.getDinBody());
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseAccountMs(dto.getDinHeader(), dto.getDinBody(),new DinError(DinErrorEnum.ACCOUNT_DELETED)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseAccountMs(dto.getDinHeader(), dto.getDinBody(),new DinError(DinErrorEnum.DELETE_ERROR)));
        }
    }

    @PostMapping("/get")
    public ResponseEntity<ResponseAccountMs> getAccountById(@RequestBody RequestMs<AccountDto> dto) {
        try {
            AESUtilAdapter adapter = new AESUtilAdapter();
            System.out.println("NUMERO DE CUENTA: "+dto.getDinBody().getNumber());

            AccountDto accountDto = accountHandler.getAccountById(dto.getDinBody());
            String number=adapter.encrypt(String.valueOf(dto.getDinBody().getNumber()));
            accountDto.setNumber(number);

            dto.getDinHeader().setSymmetricalKey(adapter.getSymmetricKey());
            dto.getDinHeader().setInitializationVector(adapter.getInitializationVector());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseAccountMs(dto.getDinHeader(),accountDto, new DinError(DinErrorEnum.SUCCESS)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseAccountMs(dto.getDinHeader(), dto.getDinBody(),new DinError(DinErrorEnum.ACCOUNT_NOT_FOUND)));
        }
    }

}
