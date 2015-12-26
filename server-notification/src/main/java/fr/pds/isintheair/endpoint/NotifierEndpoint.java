package fr.pds.isintheair.endpoint;

import com.google.gson.Gson;
import fr.pds.isintheair.PeerHandlerSingleton;
import fr.pds.isintheair.controller.MessageController;
import fr.pds.isintheair.entity.Message;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Logger;

@ServerEndpoint("/")
public class NotifierEndpoint {
    Logger logger = Logger.getLogger(this.getClass().getName());
    Gson gson = new Gson();

    @OnOpen
    public void onOpen(Session session) {
        try {
            session.getBasicRemote().sendText("Coucou");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.info(throwable.getMessage());
    }

    @OnMessage
    public String onMessage(String body, Session session) {
        Message message = gson.fromJson(body, Message.class);

        logger.info("Received message : " + body);

        MessageController.handleMessage(message, session);

        return "";
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        PeerHandlerSingleton.getInstance().removePeerSession(session);
    }
}
