package com.github.kgoedert;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    @POST
    @Operation(summary = "POSTs a new fruit to the list")
    @APIResponse(responseCode = "200", description = "Fruit registration successful")
    @APIResponse(responseCode = "500", description = "Server unavailable")
    public Response save(Fruit fruit) {
        if (fruit.getCreated() == null) {
            fruit.addCreationDate();
        }

        if (fruit.getUuid() == null) {
            fruit.addUUID();
        }
        return Response.ok(fruit).build();
    }
}