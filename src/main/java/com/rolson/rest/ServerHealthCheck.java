package com.rolson.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author rolson.quadras@gmail.com
 * @since 1.0.0
 *        <p>
 *        ServerHealthCheck JAX-RS resource class, which provides server status.
 */

@Path("/jetty")
public class ServerHealthCheck {

	@GET
	@Path("healthcheck")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		String json = "Jetty is up.";
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
}