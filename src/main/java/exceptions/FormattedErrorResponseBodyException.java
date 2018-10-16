package exceptions;

import models.errors.ErrorResponseBody;

public interface FormattedErrorResponseBodyException {

    ErrorResponseBody asErrorResponseBody();
}
