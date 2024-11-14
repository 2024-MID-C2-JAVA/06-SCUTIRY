package co.sofka.appservice.transaction.strategy;

import co.sofka.Transaction;

public class AccountMovementContext {
    public static TypeTransaction accountMovement(Transaction transaction) {
        TypeTransaction typeMovement;
        return switch (transaction.getType()){
            case BRANCH -> typeMovement=new DepositBranch();
            case ATM -> typeMovement=new DepositATM();
            case ANOTHER_ACCOUNT -> typeMovement=new DepositAnotherAccount();
            case PHYSICAL_ESTABLISHMENT -> typeMovement=new BuyPhysicalEstablishment();
            case WEB_PAGE -> typeMovement=new BuyWebPage();
            case ATM_WITHDRAWAL -> typeMovement=new Withdraw();
            default -> throw new IllegalArgumentException("Tipo de movement incorrecto "+transaction.getType());
        };
    }
}
