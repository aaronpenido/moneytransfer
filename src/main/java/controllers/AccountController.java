package controllers;

import models.Account;
import requestbodies.AccountRequestBody;
import responses.AccountResponseBody;
import services.AccountCreator;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/accounts")
public class AccountController {

    private AccountCreator accountCreator;

    @Inject
    public AccountController(AccountCreator accountCreator) {
        this.accountCreator = accountCreator;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(AccountRequestBody accountRequestBody) {
        accountRequestBody.validate();

        Account account = new Account(accountRequestBody);

        Account createdAccount = accountCreator.perform(account);

        AccountResponseBody accountResponseBody = new AccountResponseBody(createdAccount);

        return Response.status(CREATED).entity(accountResponseBody).build();
    }
}
