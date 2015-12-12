package fr.pds.isintheair;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import javax.websocket.Session;
import java.util.logging.Logger;

public class PeerHandlerSingleton {
    private BiMap<Integer, Session> tabletPeer;
    private static PeerHandlerSingleton INSTANCE = null;

    private PeerHandlerSingleton() {
        tabletPeer = HashBiMap.create();
    }

    public static synchronized PeerHandlerSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PeerHandlerSingleton();
        }

        return INSTANCE;
    }

    public synchronized void addTabletPeer(Integer userId, Session session) {
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.info("Adding user : " + userId);
        tabletPeer.put(userId, session);
    }
}
