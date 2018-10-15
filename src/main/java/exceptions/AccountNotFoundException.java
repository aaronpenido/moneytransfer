package exceptions;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(Integer accountId) {
        super(String.format("Account with id %s was not found", accountId));
    }
}
