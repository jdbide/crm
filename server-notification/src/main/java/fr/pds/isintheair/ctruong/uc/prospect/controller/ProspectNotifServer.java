package fr.pds.isintheair.ctruong.uc.prospect.controller;

import fr.pds.isintheair.ctruong.uc.prospect.websocket.ProspectNotifEndPoint;
import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;

/**
 * Created by Truong on 1/23/2016.
 */
public class ProspectNotifServer {
    public static void main(String[] args) {
        launchServer();
    }

    public static void launchServer() {
        Server serverTest = new Server("localhost", 8081, "/", ProspectNotifEndPoint.class);
        ((Runnable) () -> {
            try {
                serverTest.start();
                Thread.currentThread().join();
            } catch (DeploymentException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }).run();

    }
}
