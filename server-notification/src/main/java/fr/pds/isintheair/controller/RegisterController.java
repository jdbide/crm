package fr.pds.isintheair.controller;

import fr.pds.isintheair.PeerHandlerSingleton;
import fr.pds.isintheair.enumeration.PeerType;

import javax.websocket.Session;

public class RegisterController {
    public static void register(PeerType peerType, Integer userId, Session session) {
        PeerHandlerSingleton.getInstance().addPeer(peerType, userId, session);
    }
}
