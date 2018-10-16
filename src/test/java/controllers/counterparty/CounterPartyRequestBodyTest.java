package controllers.counterparty;

import exceptions.InvalidRequestBodyException;
import org.junit.Test;
import models.errors.ErrorResponseBodyCollection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class CounterPartyRequestBodyTest {

    @Test
    public void validateAllFields() {
        CounterPartyRequestBody counterPartyRequestBody = new CounterPartyRequestBody(null, null, null);

        Throwable exceptionThrown = catchThrowable(counterPartyRequestBody::validate);

        assertThat(exceptionThrown).isNotNull();
        assertThat(exceptionThrown).isInstanceOf(InvalidRequestBodyException.class);

        InvalidRequestBodyException invalidRequestBodyException = (InvalidRequestBodyException) exceptionThrown;

        ErrorResponseBodyCollection errors = invalidRequestBodyException.getErrorResponseBodyCollection();

        assertThat(errors.getErrors()).hasSize(3);
    }
}