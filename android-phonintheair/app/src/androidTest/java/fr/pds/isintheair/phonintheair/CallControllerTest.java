package fr.pds.isintheair.phonintheair;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.IntentFilter;
import android.test.ActivityInstrumentationTestCase2;

import fr.pds.isintheair.phonintheair.controller.CallController;
import fr.pds.isintheair.phonintheair.model.entity.Call;
import fr.pds.isintheair.phonintheair.model.entity.Message;
import fr.pds.isintheair.phonintheair.model.entity.MessageMeta;
import fr.pds.isintheair.phonintheair.model.enumeration.MessageType;
import fr.pds.isintheair.phonintheair.model.websocket.WebSocketConnectionHandlerSingleton;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/24/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class CallControllerTest extends ActivityInstrumentationTestCase2 {
    public CallControllerTest() {
        super(CallControllerTest.class);
    }

    public void testCallLaunchedOK() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_CALL);

        filter.addCategory(Intent.CATEGORY_DEFAULT);
        filter.addDataScheme("tel");

        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(filter, null, false);

        CallController.call("0610772364");

        assertTrue(activityMonitor.getHits() == 1);

        CallController.endCall();
    }


}
