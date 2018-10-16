package requestbodies;

import exceptions.InvalidRequestBodyException;
import models.AccountState;
import org.junit.Test;
import responses.ErrorResponseBodyCollection;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class AccountRequestBodyTest {

    @Test
    public void throwInvalidRequestBodyExceptionWithAllMissingFieldErrors() {
        BigDecimal nullableBalance = null;
        String nullableCurrency = null;
        Integer nullableCounterPartyId = null;

        AccountRequestBody accountRequestBody = new AccountRequestBody("Account Name", nullableBalance,
                nullableCurrency, AccountState.ACTIVE, nullableCounterPartyId);

        Throwable exceptionThrown = catchThrowable(accountRequestBody::validate);

        assertThat(exceptionThrown).isNotNull();
        assertThat(exceptionThrown).isInstanceOf(InvalidRequestBodyException.class);

        InvalidRequestBodyException invalidRequestBodyException = (InvalidRequestBodyException) exceptionThrown;

        ErrorResponseBodyCollection errors = invalidRequestBodyException.getErrorResponseBodyCollection();

        assertThat(errors.getErrors()).hasSize(3);
    }
}