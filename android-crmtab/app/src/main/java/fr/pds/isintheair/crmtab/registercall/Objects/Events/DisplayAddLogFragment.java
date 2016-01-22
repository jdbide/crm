package fr.pds.isintheair.crmtab.registercall.Objects.Events;

/**
 * Created by j-d on 09/01/2016.
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
