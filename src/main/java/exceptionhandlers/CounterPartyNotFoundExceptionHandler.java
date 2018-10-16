package exceptionhandlers;

import exceptions.CounterPartyNotFoundException;
import models.errors.ErrorResponseBody;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CounterPartyNotFoundExceptionHandler implements ExceptionMapper<CounterPartyNotFoundException> {

    @Override
    public Response toResponse(CounterPartyNotFoundException counterPartyNotFoundException) {
        ErrorResponseBody errorResponseBody = counterPartyNotFoundException.asErrorResponseBody();

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponseBody)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
