/* Copyright (C) 2015  Rolson Quadras

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.*/
package com.rolson.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * ServerHealthCheck JAX-RS resource class, which provides server status.
 * 
 * @author rolson.quadras@gmail.com
 * @since 1.0.0
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