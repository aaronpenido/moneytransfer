package models.errors;

import java.util.Set;

public class ErrorResponseBodyCollection {

    private Set<ErrorResponseBody> errors;

    public ErrorResponseBodyCollection(Set<ErrorResponseBody> errors) {
        this.errors = errors;
    }

    public Set<ErrorResponseBody> getErrors() {
        return errors;
    }
}
