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
        Message message = new Message();
        MessageMeta messageMeta = new MessageMeta();
        Call call = new Call();

        messageMeta.setMessageType(MessageType.CALL);
        message.setMessageMeta(messageMeta);
        call.setPhoneNumber(phoneNumber);
        message.setCall(call);

        try {
            phoneSession.getBasicRemote().sendText(new Gson().toJson(message, Message.class));
        }
        catch (IOException e) {
            //TODO handle failed calls
        }
    }
}
