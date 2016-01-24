package fr.pds.isintheair.controller;

import com.google.gson.Gson;
import fr.pds.isintheair.entity.Call;
import fr.pds.isintheair.entity.Message;
import fr.pds.isintheair.entity.MessageMeta;
import fr.pds.isintheair.enumeration.DeviceType;
import fr.pds.isintheair.enumeration.MessageType;

import javax.websocket.Session;
import java.io.IOException;

public class CallController {
    public static void call(Session tabletSession, String phoneNumber) {
        Session phoneSession = PeerController.getPhoneSession(tabletSession);

        if (phoneSession == null) {
            MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALL_FAILED).build();
            Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).build();

            try {
                tabletSession.getBasicRemote().sendText(new Gson().toJson(message, Message.class));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALL).build();
        Call call = new Call(phoneNumber);
        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).addCall(call).build();

        try {
            phoneSession.getBasicRemote().sendText(new Gson().toJson(message, Message.class));
        }
        catch (IOException | NullPointerException e) {
            //TODO handle failed calls
        }
    }

    public static void endCall(DeviceType deviceType, Session session) {
        Session peerSession = null;

        switch (deviceType) {
            case PHONE:
                peerSession = PeerController.getTabletSession(session);
                break;
            case TABLET:
                peerSession = PeerController.getPhoneSession(session);

        }

        MessageMeta messageMeta = new MessageMeta.MessageMetaBuilder().addMessageType(MessageType.CALL_END).build();
        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).build();

        try {
            peerSession.getBasicRemote().sendText(new Gson().toJson(message, Message.class));
        }
        catch (IOException e) {
            //TODO handle failed calls
        }
    }

    public static void notifyCallReceived(Session phoneSession, String phoneNumber) {
        Session tabletSession = PeerController.getTabletSession(phoneSession);
        MessageMeta messageMeta = MessageMeta.buildStandardMessageMeta(MessageType.CALL_RECEIVED);
        Call call = new Call(phoneNumber);
        Message message = new Message.MessageBuilder().addMessageMeta(messageMeta).addCall(call).build();

        try {
            tabletSession.getBasicRemote().sendText(new Gson().toJson(message, Message.class));
        }
        catch (IOException e) {
            //TODO handle failed calls
        }
    }
}
