package fr.pds.isintheair.ctruong.uc.prospect.websocket;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The EndPoint to connect to the websocket server
 * Created by Truong on 1/23/2016.
 *
 * @version 1.0.0
 * @since 2016-01-24
 */
@ServerEndpoint("/prospect")
public class ProspectNotifEndPoint {

    static ScheduledExecutorService timer =
            Executors.newSingleThreadScheduledExecutor();
    private static Set<Session> allSession;


    @OnOpen
    public void onOpen(Session session) {
        allSession = session.getOpenSessions();
        timer.scheduleAtFixedRate(() -> sendTimeToAll(session), 7, 1, TimeUnit.DAYS);
    }

    private void sendTimeToAll(Session session) {
        allSession = session.getOpenSessions();

        for (Session session1 : allSession) {
            try {
                session1.getBasicRemote().sendText("The analyse is finished. You've a new prospect. Let's see him");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
