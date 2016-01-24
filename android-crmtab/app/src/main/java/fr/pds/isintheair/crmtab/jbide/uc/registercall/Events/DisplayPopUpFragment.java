package fr.pds.isintheair.crmtab.jbide.uc.registercall.Events;

/**
 * Created by j-d on 09/01/2016.
 */
public class DisplayPopUpFragment {
    private CallEndedEvent callEndedEvent;

    public CallEndedEvent getCallEndedEvent() {
        return callEndedEvent;
    }

    public DisplayPopUpFragment(CallEndedEvent callEndedEvent) {

        this.callEndedEvent = callEndedEvent;
    }
}
