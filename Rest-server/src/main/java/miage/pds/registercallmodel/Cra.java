package miage.pds.registercallmodel;

import org.mongodb.morphia.annotations.Entity;

@Entity
public class Cra {

    private long iduser;
    private long idcontact;
    private String clientname;
    private String contactname;
    private String comments;
    private String subject;
    private String date;
    private int duration;
    private String calltype;


    public Cra(long iduser, long idcontact, String clientname, String contactname, String comments, String subject, String date, int duration,String calltype) {
        this.iduser = iduser;
        this.idcontact = idcontact;
        this.clientname = clientname;
        this.contactname = contactname;
        this.comments = comments;
        this.subject = subject;
        this.date = date;
        this.duration = duration;
        this.calltype = calltype;
    }

    public String getContactname() {
        return contactname;
    }

    public int getDuration() {
        return duration;
    }

    public String getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public String getComments() {
        return comments;
    }

    public String getClientname() {
        return clientname;
    }

    public long getIdcontact() {
        return idcontact;
    }

    public long getIduser() {
        return iduser;
    }

    public String getCalltype() {
        return calltype;
    }
}