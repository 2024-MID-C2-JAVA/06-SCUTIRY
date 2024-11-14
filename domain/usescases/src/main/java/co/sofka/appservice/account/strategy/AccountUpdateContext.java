package co.sofka.appservice.account.strategy;

import co.sofka.Transaction;

public class AccountUpdateContext {

    public static AccountUpdateType accountUpdate(Transaction transaction) {
        return switch (transaction.getType()) {
            case BRANCH -> new AddAmount();
            case ATM, ANOTHER_ACCOUNT, PHYSICAL_ESTABLISHMENT, WEB_PAGE, ATM_WITHDRAWAL -> new SubtractAmount();
            default -> throw new IllegalArgumentException("Tipo de transacción incorrecto " + transaction.getType());
        };
    }
}
