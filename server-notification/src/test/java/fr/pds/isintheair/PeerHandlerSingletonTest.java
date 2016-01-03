package fr.pds.isintheair;

import fr.pds.isintheair.enumeration.PeerType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.websocket.Session;

public class PeerHandlerSingletonTest {
    Session phoneSession = Mockito.mock(Session.class);
    Session tabletSession = Mockito.mock(Session.class);
    PeerHandlerSingleton peerHandlerSingleton;

    @Before
    public void setUp() {
        peerHandlerSingleton = PeerHandlerSingleton.getInstance();

        peerHandlerSingleton.addPeer(PeerType.PHONE, 42, phoneSession);
        peerHandlerSingleton.addPeer(PeerType.TABLET, 42, tabletSession);
    }

    @Test
    public void testFindPeerSession() throws Exception {
        // assertTrue(peerHandlerSingleton.findPeerSession(PeerType.PHONE, 42).equals(tabletSession));
    }

    @Test
    public void testFindPeerUserId() throws Exception {
        //assertTrue(peerHandlerSingleton.findPeerUserId(PeerType.TABLET, phoneSession) == 42);
    }
}