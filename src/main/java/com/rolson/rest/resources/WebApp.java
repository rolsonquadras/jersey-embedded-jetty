package com.rolson.rest.resources;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author rolson.quadras@gmail.com
 * @since 1.0.0
 *        <p>
 *        This class will load the configuration from web.xml to jetty
 *        standalone server.
 */
public class WebApp {

	public static void main(String[] args) throws Exception {
		String webappDirLocation = "src/main/webapp/";

		Server server = new Server(8080);
		WebAppContext root = new WebAppContext();

		root.setContextPath("/");
		root.setDescriptor("/Users/Rolson_Quadras/Documents/EWS_June15/RESTJettyDemo/src/webapp/WEB-INF/web.xml");
		// root.setDescriptor("classpath:src/webapp/WEB-INF/web.xml");
		root.setResourceBase(webappDirLocation);

		root.setParentLoaderPriority(true);

		server.setHandler(root);

		server.start();
		server.join();
	}
}