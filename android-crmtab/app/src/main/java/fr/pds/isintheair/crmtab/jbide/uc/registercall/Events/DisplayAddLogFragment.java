package fr.pds.isintheair.crmtab.jbide.uc.registercall.Events;

/**
 * Created by jbide on 24/01/2016.
 */
public class DisplayAddLogFragment {

    private CallEndedEvent callEndedEvent;

    public CallEndedEvent getCallEndedEvent() {
        return callEndedEvent;
    }

    public DisplayAddLogFragment(CallEndedEvent callEndedEvent) {

        this.callEndedEvent = callEndedEvent;
    }
}
