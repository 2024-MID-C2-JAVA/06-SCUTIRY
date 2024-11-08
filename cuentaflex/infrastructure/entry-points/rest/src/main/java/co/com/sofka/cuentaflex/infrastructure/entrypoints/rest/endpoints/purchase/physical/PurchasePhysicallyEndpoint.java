package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.purchase.physical;

import co.com.sofka.cuentaflex.domain.usecases.common.transactions.TransactionDoneResponse;
import co.com.sofka.cuentaflex.domain.usecases.common.transactions.TransactionErrors;
import co.com.sofka.cuentaflex.domain.usecases.common.transactions.UnidirectionalTransactionRequest;
import co.com.sofka.cuentaflex.domain.usecases.purchase.physical.PurchasePhysicallyUseCase;
import co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.common.dtos.TransactionDoneDto;
import co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.common.dtos.UnidirectionalTransactionDto;
import co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.common.mappers.TransactionDoneMapper;
import co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.common.mappers.UnidirectionalTransactionMapper;
import co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.constants.AccountEndpointsConstants;
import co.com.sofka.shared.domain.usecases.ResultWith;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinErrorMapper;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinRequest;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(AccountEndpointsConstants.PHYSICAL_PURCHASE_TO_ACCOUNT_URL)
@Tag(name = "Account Purchases")
public final class PurchasePhysicallyEndpoint {
    private final PurchasePhysicallyUseCase purchasePhysicallyUseCase;

    private final static Map<String, HttpStatus> ERROR_STATUS_MAP = new HashMap<>();

    static {
        ERROR_STATUS_MAP.put(TransactionErrors.ACCOUNT_NOT_FOUND.getCode(), HttpStatus.NOT_FOUND);
        ERROR_STATUS_MAP.put(TransactionErrors.INVALID_AMOUNT.getCode(), HttpStatus.BAD_REQUEST);
        ERROR_STATUS_MAP.put(TransactionErrors.INSUFFICIENT_FUNDS.getCode(), HttpStatus.BAD_REQUEST);
    }

    public PurchasePhysicallyEndpoint(PurchasePhysicallyUseCase purchasePhysicallyUseCase) {
        this.purchasePhysicallyUseCase = purchasePhysicallyUseCase;
    }

    @PostMapping
    public ResponseEntity<DinResponse<TransactionDoneDto>> purchase(
            @RequestBody DinRequest<UnidirectionalTransactionDto> requestDto
    ) {
        UnidirectionalTransactionRequest request = UnidirectionalTransactionMapper.fromDinToUseCaseRequest(requestDto);

        ResultWith<TransactionDoneResponse> result = this.purchasePhysicallyUseCase.execute(request);

        if (result.isFailure()) {
            HttpStatus status = ERROR_STATUS_MAP.getOrDefault(
                    result.getError().getCode(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

            return ResponseEntity.status(status).body(
                    DinErrorMapper.fromUseCaseToDinResponse(
                            requestDto.getDinHeader(),
                            result.getError(),
                            getErrorDetails(requestDto, result)
                    )
            );
        }

        return ResponseEntity.ok(
                TransactionDoneMapper.fromUseCaseToDinResponse(requestDto.getDinHeader(), result.getValue())
        );
    }

    private String[] getErrorDetails(DinRequest<UnidirectionalTransactionDto> requestDto, ResultWith<TransactionDoneResponse> result) {
        if(result.isSuccess()) {
            return new String[0];
        }

        if (result.getError().getCode().equals(TransactionErrors.INSUFFICIENT_FUNDS.getCode())) {
            return new String[0];
        }

        if(result.getError().getCode().equals(TransactionErrors.INVALID_AMOUNT.getCode())) {
            return new String[] {"0.00"};
        }

        if(result.getError().getCode().equals(TransactionErrors.ACCOUNT_NOT_FOUND.getCode())) {
            return new String[] {requestDto.getDinBody().getAccountId()};
        }

        return new String[0];
    }
}
