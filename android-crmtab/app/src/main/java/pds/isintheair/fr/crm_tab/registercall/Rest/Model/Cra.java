package pds.isintheair.fr.crm_tab.registercall.Rest.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by j-d on 22/12/2015.
 */
public class Cra {

    @SerializedName("calltype")
    @Expose
    private String calltype;
    @SerializedName("clientname")
    @Expose
    private String clientname;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("contactname")
    @Expose
    private String contactname;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("duration")
    @Expose
    private Long duration;
    @SerializedName("idcontact")
    @Expose
    private Long idcontact;
    @SerializedName("iduser")
    @Expose
    private Long iduser;
    @SerializedName("subject")
    @Expose
    private String subject;

    /**
     *
     * @return
     * The calltype
     */
    public String getCalltype() {
        return calltype;
    }

    /**
     *
     * @param calltype
     * The calltype
     */
    public void setCalltype(String calltype) {
        this.calltype = calltype;
    }

    /**
     *
     * @return
     * The clientname
     */
    public String getClientname() {
        return clientname;
    }

    /**
     *
     * @param clientname
     * The clientname
     */
    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    /**
     *
     * @return
     * The comments
     */
    public String getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     * The comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     * The contactname
     */
    public String getContactname() {
        return contactname;
    }

    /**
     *
     * @param contactname
     * The contactname
     */
    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The duration
     */
    public Long getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     * The duration
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     *
     * @return
     * The idcontact
     */
    public Long getIdcontact() {
        return idcontact;
    }

    /**
     *
     * @param idcontact
     * The idcontact
     */
    public void setIdcontact(Long idcontact) {
        this.idcontact = idcontact;
    }

    /**
     *
     * @return
     * The iduser
     */
    public Long getIduser() {
        return iduser;
    }

    /**
     *
     * @param iduser
     * The iduser
     */
    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    /**
     *
     * @return
     * The subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @param subject
     * The subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

   /* private long iduser;
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
    }*/
}
