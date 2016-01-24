package fr.pds.isintheair.jdatour.uc.phone.call.receive.controller;

import fr.pds.isintheair.jdatour.uc.phone.call.receive.enumeration.DeviceType;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.peer.PeerHandlerSingleton;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

import static org.mockito.Matchers.anyString;
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

        peerHandlerSingleton.addPeer(DeviceType.PHONE, 42, phoneSession);
        peerHandlerSingleton.addPeer(DeviceType.TABLET, 42, tabletSession);

        when(phoneSession.getBasicRemote()).thenReturn(Mockito.mock(RemoteEndpoint.Basic.class));
        doReturn(42).when(peerHandlerSingleton).findPeerUserId(DeviceType.TABLET, tabletSession);

        CallController.call(tabletSession, "0610772364");

        verify(phoneSession.getBasicRemote(), times(1)).sendText(anyString());
    }
}