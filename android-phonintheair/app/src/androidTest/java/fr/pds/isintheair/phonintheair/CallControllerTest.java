package fr.pds.isintheair.phonintheair;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.test.ActivityTestCase;

import fr.pds.isintheair.phonintheair.controller.CallController;

public class CallControllerTest extends ActivityTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        PhointheairApp.context = getActivity();
    }

    public void testCall() {
        CallController.call("0610772364");

        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(
                Context.TELEPHONY_SERVICE);
    }
}
