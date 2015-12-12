package fr.pds.isintheair;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import fr.pds.isintheair.enumeration.PeerType;

import javax.websocket.Session;

public class PeerHandlerSingleton {
    private BiMap<Integer, Session> phonePeer;
    private BiMap<Integer, Session> tabletPeer;
    private BiMap<Session, Session> sessionPeer;
    private static PeerHandlerSingleton INSTANCE = null;

    private PeerHandlerSingleton() {
        phonePeer = HashBiMap.create();
        tabletPeer = HashBiMap.create();
    }

    public static synchronized PeerHandlerSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PeerHandlerSingleton();
        }

        return INSTANCE;
    }

    public synchronized void addPeer(PeerType peerType, Integer userId, Session session) {
        switch (peerType) {
            case PHONE:
                phonePeer.put(userId, session);
                break;
            case TABLET:
                tabletPeer.put(userId, session);
                break;
        }
    }

    public synchronized Session findPeerSession(PeerType peerType, Integer userId) {
        switch (peerType) {
            case PHONE:
                return phonePeer.get(userId);
            case TABLET:
                return tabletPeer.get(userId);
        }

        return null;
    }

    public synchronized Integer findPeerUserId(PeerType peerType, Session session) {
        switch (peerType) {
            case PHONE:
                return phonePeer.inverse().get(session);
            case TABLET:
                return tabletPeer.inverse().get(session);
        }

        return null;
    }
}
