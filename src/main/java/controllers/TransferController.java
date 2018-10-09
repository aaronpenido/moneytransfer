package controllers;

import com.google.gson.JsonObject;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/transfers")
public class TransferController {

    @POST
    @Produces("application/json")
    public String post()
    {
        JsonObject responseBody = new JsonObject();

        responseBody.addProperty("id", "123");

        return responseBody.toString();
    }
}
