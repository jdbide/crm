package pds.isintheair.fr.crm_tab.registercall;

/**
 * Created by j-d on 20/12/2015.
 */
public class CallEndedEvent {

    private String idcontact;
    private String idclient;
    private String duration;
    private String date;
    private Boolean callemitted;

    public CallEndedEvent(Boolean callemitted, String date, String duration, String idclient, String idcontact) {
        this.callemitted = callemitted;
        this.date = date;
        this.duration = duration;
        this.idclient = idclient;
        this.idcontact = idcontact;
    }

    public String getIdcontact() {
        return idcontact;
    }

    public Boolean getCallemitted() {
        return callemitted;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public String getIdclient() {
        return idclient;
    }
}
