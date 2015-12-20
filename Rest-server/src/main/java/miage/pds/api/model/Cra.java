package miage.pds.api.model;

import java.util.HashMap;
import java.util.Map;

public class Cra {
	private Integer iduser;
    private Integer idcontact;
    private Integer contactnumber;
    private Integer duration;
    private String calldate;
    private String subject;
    private String comment;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Cra(Integer iduser, Integer idcontact, Integer contactnumber,Integer duration, String subject, String calldate, String comment, Map<String, Object> additionalProperties) {
        this.iduser = iduser;
        this.idcontact = idcontact;
        this.contactnumber = contactnumber;
        this.duration = duration;
        this.subject = subject;
        this.calldate = calldate;
        this.comment = comment;
        this.additionalProperties = additionalProperties;
    }

    /**
     *
     * @return
     * The iduser
     */
    public Integer getIduser() {
        return iduser;
    }

    /**
     *
     * @param iduser
     * The iduser
     */
    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    /**
     *
     * @return
     * The idcontact
     */
    public Integer getIdcontact() {
        return idcontact;
    }

    /**
     *
     * @param idcontact
     * The idcontact
     */
    public void setIdcontact(Integer idcontact) {
        this.idcontact = idcontact;
    }

    /**
     *
     * @return
     * The contactnumber
     */
    public Integer getContactnumber() {
        return contactnumber;
    }

    /**
     *
     * @param contactnumber
     * The contactnumber
     */
    public void setContactnumber(Integer contactnumber) {
        this.contactnumber = contactnumber;
    }

    /**
     *
     * @return
     * The duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     * The duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     *
     * @return
     * The calldate
     */
    public String getCalldate() {
        return calldate;
    }

    /**
     *
     * @param calldate
     * The calldate
     */
    public void setCalldate(String calldate) {
        this.calldate = calldate;
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

    /**
     *
     * @return
     * The comment
     */
    public String getComment() {
        return comment;
    }

    /**
     *
     * @param comment
     * The comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
