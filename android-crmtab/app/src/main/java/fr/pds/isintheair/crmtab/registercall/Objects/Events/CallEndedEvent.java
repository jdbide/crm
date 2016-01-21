package fr.pds.isintheair.crmtab.registercall.Objects.Events;

import fr.pds.isintheair.crmtab.registercall.Objects.CallType;

/**
 * Created by j-d on 20/12/2015.
 */
public class CallEndedEvent {

    private String idcontact;
    private String duration;
    private String date;
    private CallType callemitted;
    private boolean displaypopup;

    public CallEndedEvent(CallType callemitted, String date, String duration, String idcontact,boolean b) {
        this.callemitted = callemitted;
        this.date = date;
        this.duration = duration;
        this.idcontact = idcontact;
        displaypopup = b;
    }

    public boolean getDisplaypopUp() {
        return displaypopup;
    }
    public String getIdcontact() {
        return idcontact;
    }

    public CallType getCalltype() {
        return callemitted;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

}
