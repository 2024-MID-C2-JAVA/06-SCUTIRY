package co.sofka;

public interface AccountRepository {
    void createAccount(Account account);
    void deleteAccount(Account account);
    Account getAccount(Account account);
    void updateAccount(Account account);
}
