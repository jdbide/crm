package fr.pds.isintheair.crmtab.jbide.uc.registercall.Events;

import fr.pds.isintheair.crmtab.jbide.uc.registercall.enums.CallType;

/**
 * Created by j-d on 20/12/2015.
 */
public class CallEndedEvent {

    private String idcontact;
    private String duration;
    private String date;
    private CallType callemitted;


    public CallEndedEvent(){

    }

    public CallEndedEvent(CallType callemitted, String date, String duration, String idcontact) {
        this.callemitted = callemitted;
        this.date = date;
        this.duration = duration;
        this.idcontact = idcontact;

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
