package controllers.counterparty;

import models.counterparty.CounterParty;
import services.CounterPartyCreator;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/counterparties")
public class CounterPartyController {

    private CounterPartyCreator counterPartyCreator;

    @Inject
    CounterPartyController(CounterPartyCreator counterPartyCreator) {
        this.counterPartyCreator = counterPartyCreator;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(CounterPartyRequestBody counterPartyRequestBody) {
        counterPartyRequestBody.validate();

        CounterParty counterParty = new CounterParty(counterPartyRequestBody);

        CounterParty createdCounterParty = counterPartyCreator.perform(counterParty);

        CounterPartyResponseBody counterPartyResponseBody = new CounterPartyResponseBody(createdCounterParty);

        return Response.status(CREATED).entity(counterPartyResponseBody).build();
    }
}
