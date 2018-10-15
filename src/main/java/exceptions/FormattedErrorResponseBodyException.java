package exceptions;

import responses.ErrorResponseBody;

public interface FormattedErrorResponseBodyException {

    ErrorResponseBody asErrorResponseBody();
}
