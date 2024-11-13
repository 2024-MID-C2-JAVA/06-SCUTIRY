package din;

public enum DinErrorEnum {

    SUCCESS("0", "1000", "Operation successful", "SUCCESS"),
    OPERATION_FAILED("1", "1001", "Operation failed", "ERROR"),
    NOT_FOUND("1", "1004", "Not found", "ERROR"),
    INTERNAL_SERVER_ERROR("1", "1005", "Internal server error", "ERROR"),
    UNKNOWN_ERROR("1", "1006", "Unknown error", "ERROR"),

    CUSTOMER_CREATED("0", "1000", "Customer created successfully", "SUCCESS"),
    CUSTOMER_NOT_FOUND("2", "1002", "Customer not found", "WARNING"),
    CUSTOMER_DELETED("0", "1003", "Customer deleted successfully", "SUCCESS"),
    CREATION_ERROR("1", "1010", "Creation failed", "ERROR"),

    ACCOUNT_CREATED("0", "2000", "Account created successfully", "SUCCESS"),
    ACCOUNT_NOT_FOUND("2", "2001", "Account does not exist", "WARNING"),
    ACCOUNT_DELETED("0", "2002", "Account deleted successfully", "SUCCESS"),
    ACCOUNT_UPDATED_ERROR("1", "2007", "An error occurred while the account was updating", "ERROR"),

    TRANSACTION_ERROR("1", "3000", "Transaction failed", "ERROR"),
    DELETE_ERROR("1", "2004", "Error deleting customer", "ERROR"),
    TRANSACTION_CREATE("0", "1000", "Transaction created", "SUCCESS");

    private final String code;
    private final String codeErrorProvider;
    private final String message;
    private final String type;

    DinErrorEnum(String code, String errorCodeProveedor, String message, String type) {
        this.code = code;
        this.codeErrorProvider = errorCodeProveedor;
        this.message = message;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public String getErrorCodeProvider() {
        return codeErrorProvider;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}