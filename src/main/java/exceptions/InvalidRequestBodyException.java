package exceptions;

import models.errors.ErrorResponseBodyCollection;

public class InvalidRequestBodyException extends RuntimeException {

    private ErrorResponseBodyCollection errorResponseBodyCollection;

    public InvalidRequestBodyException(ErrorResponseBodyCollection errorResponseBodyCollection) {
        this.errorResponseBodyCollection = errorResponseBodyCollection;
    }

    public ErrorResponseBodyCollection getErrorResponseBodyCollection() {
        return errorResponseBodyCollection;
    }
}
