package exceptions;

import models.errors.ErrorResponseBody;

public class CounterPartyNotFoundException extends RuntimeException implements FormattedErrorResponseBodyException {

    public CounterPartyNotFoundException(Integer counterPartyId) {
        super(String.format("Counter Party with id %s was not found", counterPartyId));
    }

    @Override
    public ErrorResponseBody asErrorResponseBody() {
        String code = "counterPartyNotFound";

        return new ErrorResponseBody(code, this.getMessage());
    }
}
