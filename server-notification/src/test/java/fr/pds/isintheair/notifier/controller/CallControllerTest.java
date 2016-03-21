package fr.pds.isintheair.notifier.controller;

public class CallControllerTest {
    /* Session phoneSession = Mockito.mock(Session.class);
    Session tabletSession = Mockito.mock(Session.class);

    PeerHandlerSingleton peerHandlerSingleton;

    @Before
    public void setUp() {
        peerHandlerSingleton = spy(PeerHandlerSingleton.getInstance());

        peerHandlerSingleton.addPeer(DeviceType.PHONE, 42, phoneSession);
        peerHandlerSingleton.addPeer(DeviceType.TABLET, 42, tabletSession);

        when(phoneSession.getBasicRemote()).thenReturn(Mockito.mock(RemoteEndpoint.Basic.class));
        doReturn(42).when(peerHandlerSingleton).findPeerUserId(DeviceType.TABLET, tabletSession);
    }

    @Test
    public void testCallLaunched() throws Exception {
        CallController.call(tabletSession, "0610772364");
        verify(phoneSession.getBasicRemote(), times(1)).sendText(anyString());
    }

    @Test
    public void testCallLaunchedCorrectly() throws Exception {
        MessageInfo messageInfo = new MessageInfo.MessageInfoBuilder().addMessageType(MessageType.CALL).build();
        Call        call        = new Call("0610772364");
        Message     message     = new Message.MessageBuilder().addMessageMeta(messageInfo).addCall(call).build();

        CallController.call(tabletSession, "0610772364");

        verify(phoneSession.getBasicRemote(), times(1)).sendText(JSONHelper.serialize(message, Message.class));
    } */
}