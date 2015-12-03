package app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);		// SLF4 facade logger.
    private Server server = null;													// The Jetty Embbeded Server
    private final AnnotationConfigWebApplicationContext webSpringContext = new AnnotationConfigWebApplicationContext();		// Actual Spring Entry Point.
    private short port;
    private ServletContextHandler handler = new ServletContextHandler();			// The Servlet Context Handler

    /**
     *
     * @throws Exception
     */
    public Application() throws Exception {

        port = Short.parseShort(System.getProperty("server.http.port", "8080"));
        webSpringContext.setConfigLocation("config");

        handler.setContextPath("/");
        handler.addServlet(new ServletHolder(new DispatcherServlet(webSpringContext)), "/*");
        handler.addEventListener(new ContextLoaderListener(webSpringContext));

        server = new Server(port);
        server.setHandler(handler);
        server.start();
        server.join();
    }

    public static void main(String[] args) throws Exception {
        new Application();
    }
}