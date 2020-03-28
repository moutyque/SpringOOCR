import static org.junit.Assert.*;

import java.net.InetSocketAddress;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;
import javax.xml.ws.spi.http.HttpHandler;

import org.junit.Test;

public class TestWebService {

	@Test
	public void test() {
//		URI uri = UriBuilder.fromUri("http://localhost/").port(8080).build();
//		 
//        // Create an HTTP server listening at port 8282
//        HttpServer server = HttpServer.create(new InetSocketAddress(uri.getPort()), 0);
//        // Create a handler wrapping the JAX-RS application
//        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new ApplicationConfig(), HttpHandler.class);
//        // Map JAX-RS handler to the server root
//        server.createContext(uri.getPath(), handler);
//        // Start the server
//        server.start();
// 
//        Client client = ClientFactory.newClient();
// 
//        // Valid URIs
//        assertEquals(200, client.target("http://localhost:8282/customer/agoncal").request().get().getStatus());
//      
// 
//        // Stop HTTP server
//        server.stop(0);
	}

}
