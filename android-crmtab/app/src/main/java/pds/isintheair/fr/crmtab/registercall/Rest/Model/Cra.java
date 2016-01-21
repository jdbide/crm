package pds.isintheair.fr.crmtab.registercall.Rest.Model;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import pds.isintheair.fr.crmtab.OrmTabDataBase;

/**
 * Created by j-d on 22/12/2015.
 */
@Table(databaseName = OrmTabDataBase.DBNAME)
public class Cra extends BaseModel {


    @PrimaryKey(autoincrement = true)
    @Column
    public int id;
    @Column
    private String calltype;
    @Column
    private String clientname;
    @Column
    private String comments;
    @Column
    private String contactname;
    @Column
    private String date;
    @Column
    private Long duration;
    @Column
    private Long idcontact;
    @Column
    private Long iduser;
    @Column
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

    public void setId(int id) {
        this.id = id;
    }
}
