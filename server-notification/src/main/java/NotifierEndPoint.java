import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/notifier")
public class NotifierEndpoint {
    @OnOpen
    public void onOpen(Session session) {
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        return message;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
    }
}
