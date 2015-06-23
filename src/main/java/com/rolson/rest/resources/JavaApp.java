package com.rolson.rest.resources;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * @author rolson.quadras@gmail.com
 * @since 1.0.0
 *        <p>
 *        This class will load the configuration from java class to jetty
 *        standalone server.
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