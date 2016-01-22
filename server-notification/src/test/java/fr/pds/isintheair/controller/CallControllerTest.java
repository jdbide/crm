package fr.pds.isintheair.controller;

import fr.pds.isintheair.PeerHandlerSingleton;
import fr.pds.isintheair.entity.Call;
import fr.pds.isintheair.enumeration.PeerType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class CallControllerTest {
    PeerHandlerSingleton peerHandlerSingleton;

    @Before
    public void setUp() {
    }

    @Test
    public void testCall() throws Exception {
        Session phoneSession = Mockito.mock(Session.class);
        Session tabletSession = Mockito.mock(Session.class);

        peerHandlerSingleton = spy(PeerHandlerSingleton.getInstance());

        peerHandlerSingleton.addPeer(PeerType.PHONE, 42, phoneSession);
        peerHandlerSingleton.addPeer(PeerType.TABLET, 42, tabletSession);

        when(phoneSession.getBasicRemote()).thenReturn(Mockito.mock(RemoteEndpoint.Basic.class));
        doReturn(42).when(peerHandlerSingleton).findPeerUserId(PeerType.TABLET, tabletSession);

        CallController.call(tabletSession, "0610772364");

        verify(phoneSession.getBasicRemote(), times(1)).sendText(anyString());
    }
}