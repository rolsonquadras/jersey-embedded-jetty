/* Copyright (C) 2015  Rolson Quadras

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.*/
package com.rolson.rest.resources;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * This class will load the configuration from java class to Jetty standalone
 * server.
 * 
 * @author rolson.quadras@gmail.com
 * @since 1.0.0
 */
public class JavaApp {
	public static void main(String[] args) throws Exception {
		ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		context.setContextPath("/rest/");

		Server jettyServer = new Server(8080);
		jettyServer.setHandler(context);

		ServletHolder jerseyServlet = context.addServlet(
				org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);

		// Tells the Jersey Servlet which REST service/class to load.
		jerseyServlet.setInitParameter(
				"com.sun.jersey.spi.container.ContainerRequestFilters",
				"com.rolson.rest.filter.PreMatchingFilter");
		jerseyServlet.setInitParameter(
				"jersey.config.server.provider.packages", "com.rolson.rest");

		try {
			jettyServer.start();
			jettyServer.join();
		} finally {
			jettyServer.destroy();
		}
	}
}