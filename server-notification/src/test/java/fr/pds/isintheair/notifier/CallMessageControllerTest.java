package fr.pds.isintheair.notifier;

public class CallMessageControllerTest {
    /* Session phoneSession = Mockito.mock(Session.class);
    Session tabletSession = Mockito.mock(Session.class);

    PeerHandlerSingleton peerHandlerSingleton;

    @Before
    public void setUp() {
    }

    @Test
    public void testMessageRegisterPhoneAddPeer() {
        MessageInfo messageInfo = new MessageInfo.MessageInfoBuilder().addMessageType(MessageType.REGISTER_PHONE).build();
        Register    register    = new Register(42);
        Message     message     = new Message.MessageBuilder().addMessageMeta(messageInfo).addRegister(register).build();

        CallMessageController.handleMessage(message, phoneSession);

        assertNotNull(PeerHandlerSingleton.getInstance().findPeerSession(DeviceType.PHONE, 42));
        assertEquals((int) PeerHandlerSingleton.getInstance().findPeerUserId(DeviceType.PHONE, phoneSession), 42);
    }

    @Test
    public void testMessageRegisterTabletAddPeer() {
        MessageInfo messageInfo = new MessageInfo.MessageInfoBuilder().addMessageType(MessageType.REGISTER_TABLET).build();
        Register    register    = new Register(42);
        Message     message     = new Message.MessageBuilder().addMessageMeta(messageInfo).addRegister(register).build();

        CallMessageController.handleMessage(message, tabletSession);

        assertNotNull(PeerHandlerSingleton.getInstance().findPeerSession(DeviceType.TABLET, 42));
        assertEquals((int) PeerHandlerSingleton.getInstance().findPeerUserId(DeviceType.TABLET, tabletSession), 42);
    } */
}
