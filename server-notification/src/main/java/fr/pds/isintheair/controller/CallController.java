package fr.pds.isintheair.controller;

import com.google.gson.Gson;
import fr.pds.isintheair.entity.Call;
import fr.pds.isintheair.entity.Message;
import fr.pds.isintheair.entity.MessageMeta;
import fr.pds.isintheair.enumeration.MessageType;

import javax.websocket.Session;
import java.io.IOException;

public class CallController {
    public static void call(Session tabletSession, Long phoneNumber) {
        Session phoneSession = PeerController.getPhoneSession(tabletSession);
        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALL).build();
        Call call = new Call(phoneNumber);
        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).addCall(call).build();

        try {
            phoneSession.getBasicRemote().sendText(new Gson().toJson(message, Message.class));
        }
        catch (IOException e) {
            //TODO handle failed calls
        }
    }
}
