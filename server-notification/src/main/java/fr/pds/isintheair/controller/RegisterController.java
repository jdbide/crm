package fr.pds.isintheair.controller;

import fr.pds.isintheair.PeerHandlerSingleton;

import javax.websocket.Session;

public class RegisterController {
    public static void registerTablet(Integer userId, Session session) {
        PeerHandlerSingleton.getInstance().addTabletPeer(userId, session);
        session.getAsyncRemote().sendText("OK");
    }
}
