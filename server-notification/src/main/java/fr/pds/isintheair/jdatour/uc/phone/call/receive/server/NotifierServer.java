package fr.pds.isintheair.jdatour.uc.phone.call.receive.server;

import org.glassfish.tyrus.server.Server;

public class NotifierServer {

    public static void main(String[] args) {
        runServer();
    }

    public static void runServer() {
        Server server = new Server("localhost", 8090, "/", NotifierEndpoint.class);

        ((Runnable) () -> {
            try {
                server.start();
                Thread.currentThread().join();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            finally {
                server.stop();
            }
        }).run();
    }
}
