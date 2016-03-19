package fr.pds.isintheair.jdatour.uc.phone.call.receive.server;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Logger;

@ServerEndpoint("/calendar")
public class CalendarNotifierEndpoint {
    private final static Logger logger = Logger.getLogger(CallNotifierEndpoint.class.getName());

    @OnOpen
    public void onOpen(Session session) throws IOException {
        logger.info("Calendar session opened");
        session.getBasicRemote().sendText("Hello i'm calendar notifier");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.info("Error : " + throwable.getMessage());
    }

    @OnMessage
    public void onMessage(String body, Session session) throws IOException {
        session.getBasicRemote().sendText("Hello i'm calendar notifier");
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info("Cloded session : " + closeReason.getReasonPhrase());
    }
}
