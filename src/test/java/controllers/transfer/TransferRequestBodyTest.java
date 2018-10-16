package controllers.transfer;

import exceptions.InvalidRequestBodyException;
import org.junit.Test;
import models.errors.ErrorResponseBody;
import models.errors.ErrorResponseBodyCollection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class TransferRequestBodyTest {

    @Test
    public void validateAllFieldsButDescription() {
        TransferRequestBody transferRequestBody = new TransferRequestBody(null, null,
                null, null, null, null);

        Throwable exceptionThrown = catchThrowable(transferRequestBody::validate);

        assertThat(exceptionThrown).isNotNull();
        assertThat(exceptionThrown).isInstanceOf(InvalidRequestBodyException.class);

        InvalidRequestBodyException invalidRequestBodyException = (InvalidRequestBodyException) exceptionThrown;

        ErrorResponseBodyCollection errors = invalidRequestBodyException.getErrorResponseBodyCollection();

        assertThat(errors.getErrors()).hasSize(5);
    }

    @Test
    public void validateIfSchedulerForFieldIsNotBeforeCurrentDate() {
        LocalDate yesterday = LocalDate.now().minusDays(1);

        TransferRequestBody transferRequestBody = new TransferRequestBody(1, 1,
                BigDecimal.TEN, "EUR", "Description", yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        Throwable exceptionThrown = catchThrowable(transferRequestBody::validate);

        assertThat(exceptionThrown).isNotNull();
        assertThat(exceptionThrown).isInstanceOf(InvalidRequestBodyException.class);

        InvalidRequestBodyException invalidRequestBodyException = (InvalidRequestBodyException) exceptionThrown;

        ErrorResponseBodyCollection errors = invalidRequestBodyException.getErrorResponseBodyCollection();

        ErrorResponseBody expectedError = new ErrorResponseBody(
                "invalidScheduledFor", "ScheduledFor must be equals or after the current date");

        assertThat(errors.getErrors().iterator().next()).isEqualToComparingFieldByFieldRecursively(expectedError);
    }
}