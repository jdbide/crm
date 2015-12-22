package pds.isintheair.fr.crm_tab.registercall.Rest.Model;

/**
 * Created by j-d on 22/12/2015.
 */
public class Cra {

    private int iduser;
    private int idcontact;
    private String clientname;
    private String contactname;
    private String comments;
    private String subject;
    private String date;
    private int duration;

    public Cra(int iduser, int idcontact, String clientname, String contactname, String comments, String subject, String date, int duration) {
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
