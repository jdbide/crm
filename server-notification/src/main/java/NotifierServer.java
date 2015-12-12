import org.glassfish.tyrus.server.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by ohpre on 12/12/2015.
 */
public class NotifierServer {

    public static void main(String[] args) {
        runServer();
    }

    public static void runServer() {
        Server server = new Server("localhost", 8080, "/", NotifierEndpoint.class);

        try {
            server.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            reader.readLine();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            server.stop();
        }
    }

}
