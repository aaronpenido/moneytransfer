package exceptionhandlers;

import exceptions.InvalidRequestBodyException;
import models.errors.ErrorResponseBodyCollection;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidRequestBodyExceptionHandler implements ExceptionMapper<InvalidRequestBodyException> {

    @Override
    public Response toResponse(InvalidRequestBodyException invalidRequestBodyException) {
        ErrorResponseBodyCollection errorCollection = invalidRequestBodyException.getErrorResponseBodyCollection();

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorCollection)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
