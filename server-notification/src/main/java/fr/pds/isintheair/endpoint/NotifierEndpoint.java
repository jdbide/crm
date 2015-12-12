package fr.pds.isintheair.endpoint;

import com.google.gson.Gson;
import fr.pds.isintheair.controller.MessageController;
import fr.pds.isintheair.entity.Message;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.logging.Logger;

@ServerEndpoint("/notifier")
public class NotifierEndpoint {
    Logger logger = Logger.getLogger(this.getClass().getName());
    Gson gson = new Gson();

    @OnOpen
    public void onOpen(Session session) {
    }

    @OnMessage
    public String onMessage(String body, Session session) {
        Message message = gson.fromJson(body, Message.class);

        MessageController.handleMessage(message, session);

        return body;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
    }
}
