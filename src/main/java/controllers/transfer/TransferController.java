package controllers.transfer;

import models.transfer.Transfer;
import services.TransferCreator;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/transfers")
public class TransferController {

    private TransferCreator transferCreator;

    @Inject
    TransferController(TransferCreator transferCreator) {
        this.transferCreator = transferCreator;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(TransferRequestBody transferRequestBody) {
        transferRequestBody.validate();

        Transfer transfer = new Transfer(transferRequestBody);

        Transfer savedTransfer = transferCreator.perform(transfer);

        TransferResponseBody transferResponseBody = new TransferResponseBody(savedTransfer);

        return Response.status(CREATED).entity(transferResponseBody).build();
    }
}