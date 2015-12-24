package pds.isintheair.fr.crm_tab.registercall;

/**
 * Created by j-d on 20/12/2015.
 */
public class CallEndedEvent {

    private int idcontact;
    private int idclient;
    private int duration;
    private String date;
    private Boolean callemitted;

    public CallEndedEvent(Boolean callemitted, String date, int duration, int idclient, int idcontact) {
        this.callemitted = callemitted;
        this.date = date;
        this.duration = duration;
        this.idclient = idclient;
        this.idcontact = idcontact;
    }

    public int getIdcontact() {
        return idcontact;
    }

    public Boolean getCallemitted() {
        return callemitted;
    }

    public String getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

    public int getIdclient() {
        return idclient;
    }
}
