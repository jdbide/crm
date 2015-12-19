package fr.pds.isintheair.controller;

import fr.pds.isintheair.PeerHandlerSingleton;
import fr.pds.isintheair.enumeration.PeerType;

import javax.websocket.Session;

public class PeerController {
    public static Session getPhoneSession(Session tabletSession) {
        PeerHandlerSingleton peerHandlerSingleton = PeerHandlerSingleton.getInstance();
        Integer userId = peerHandlerSingleton.findPeerUserId(PeerType.TABLET, tabletSession);

        return peerHandlerSingleton.findPeerSession(PeerType.PHONE, userId);
    }

    public static Session getTabletSession(Integer userId) {
        return null;
    }
}
