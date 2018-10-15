package exceptions;

import responses.ErrorResponseBody;

public class AccountNotFoundException extends RuntimeException implements FormattedErrorResponseBodyException  {

    public AccountNotFoundException(Integer accountId) {
        super(String.format("Account with id %s was not found", accountId));
    }

    @Override
    public ErrorResponseBody asErrorResponseBody() {
        String code = "accountNotFound";

        return new ErrorResponseBody(code, this.getMessage());
    }
}
