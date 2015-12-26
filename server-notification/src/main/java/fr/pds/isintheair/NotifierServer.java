package fr.pds.isintheair;

import fr.pds.isintheair.endpoint.NotifierEndpoint;
import org.glassfish.tyrus.server.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NotifierServer {

    public static void main(String[] args) {
        runServer();
    }

    public static void runServer() {
        Server server = new Server("localhost", 8090, "/", NotifierEndpoint.class);

        ((Runnable) () -> {
            try {
                server.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                reader.readLine();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                server.stop();
            }
        }).run();
        }
    }
