package fr.pds.isintheair.jdatour.uc.phone.call.receive.server;

import fr.pds.isintheair.jdatour.uc.phone.call.receive.controller.MessageController;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.entity.Message;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.helper.JSONHelper;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.peer.PeerHandlerSingleton;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/")
public class NotifierEndpoint {
    @OnOpen
    public void onOpen(Session session) {
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
    }

    @OnMessage
    public void onMessage(String body, Session session) {
        Message message = (Message) JSONHelper.deserialize(body, Message.class);

        MessageController.handleMessage(message, session);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        PeerHandlerSingleton.getInstance().removePeerSession(session);
    }
}
