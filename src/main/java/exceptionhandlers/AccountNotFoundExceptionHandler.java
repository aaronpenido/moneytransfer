package exceptionhandlers;

import exceptions.AccountNotFoundException;
import models.errors.ErrorResponseBody;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccountNotFoundExceptionHandler implements ExceptionMapper<AccountNotFoundException> {

    @Override
    public Response toResponse(AccountNotFoundException accountNotFoundException) {
        ErrorResponseBody errorResponseBody = accountNotFoundException.asErrorResponseBody();

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponseBody)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
