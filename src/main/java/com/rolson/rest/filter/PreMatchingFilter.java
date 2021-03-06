/* Copyright (C) 2015  Rolson Quadras

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.*/
package com.rolson.rest.filter;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.Provider;

/**
 * This class will intercept the http request, prints the header values and
 * checks for the SESSIONID paramter in the header. Throws exception in case the
 * SESSIONID doesn't macth the hardcoded value.
 * 
 * @author rolson.quadras@gmail.com
 * @since 1.0.0
 */
@Provider
@PreMatching
public class PreMatchingFilter implements ContainerRequestFilter {

	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		for (String header : requestContext.getHeaders().keySet()) {
			System.out.print(header + "  "
					+ requestContext.getHeaderString(header) + "\n");

		}
		if (requestContext.getHeaderString("SEESIONID") == null
				|| !requestContext.getHeaderString("SEESIONID")
						.equalsIgnoreCase("@123#")) {

			ResponseBuilder builder = null;
			String response = "UNAUTHORISED ACCESS";
			builder = Response.status(Response.Status.UNAUTHORIZED).entity(
					response);
			throw new WebApplicationException(builder.build());

		}
	}
}
