package co.sofka;

import co.sofka.data.transaction.ResponseTransactionMs;
import co.sofka.data.transaction.TransactionDto;
import co.sofka.handler.TransactionHandler;
import din.DinError;
import din.DinErrorEnum;
import din.RequestMs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionRestController {

    private final TransactionHandler transactionHandler;

    public TransactionRestController(TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseTransactionMs> createTransaction(@RequestBody RequestMs<TransactionDto> dto) {
        try {
            transactionHandler.createTransaction(dto.getDinBody());
            return ResponseEntity.ok(new ResponseTransactionMs(dto.getDinHeader(), dto.getDinBody(), new DinError(DinErrorEnum.TRANSACTION_CREATE)));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseTransactionMs(dto.getDinHeader(), dto.getDinBody(), new DinError(DinErrorEnum.TRANSACTION_ERROR)));
        }
    }

    @PostMapping("/get")
    public ResponseEntity<ResponseTransactionMs> getTransaction(@RequestBody RequestMs<TransactionDto> dto) {
        try {
            System.out.println("DTO ID: "+dto.getDinBody().getId());
            List<TransactionDto> transactionDtos = transactionHandler.getTransactionsByUserId(dto.getDinBody());
            return ResponseEntity.ok(new ResponseTransactionMs(dto.getDinHeader(),transactionDtos, new DinError(DinErrorEnum.TRANSACTION_CREATE)));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseTransactionMs(dto.getDinHeader(), dto.getDinBody(), new DinError(DinErrorEnum.TRANSACTION_ERROR)));
        }
    }

}
