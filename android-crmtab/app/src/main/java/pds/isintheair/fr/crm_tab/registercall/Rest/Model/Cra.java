package pds.isintheair.fr.crm_tab.registercall.Rest.Model;

/**
 * Created by j-d on 22/12/2015.
 */
public class Cra {

    private long iduser;
    private long idcontact;
    private String clientname;
    private String contactname;
    private String comments;
    private String subject;
    private String date;
    private int duration;


    public Cra(long iduser, long idcontact, String clientname, String contactname, String comments, String subject, String date, int duration) {
        this.iduser = iduser;
        this.idcontact = idcontact;
        this.clientname = clientname;
        this.contactname = contactname;
        this.comments = comments;
        this.subject = subject;
        this.date = date;
        this.duration = duration;
    }
}
