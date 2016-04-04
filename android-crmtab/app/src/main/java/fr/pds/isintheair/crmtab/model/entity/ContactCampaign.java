package fr.pds.isintheair.crmtab.model.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

import fr.pds.isintheair.crmtab.model.database.OrmTabDataBase;

/**
 * Created by tlacouque on 29/03/2016.
 */
@Table(database = OrmTabDataBase.class)
public class ContactCampaign extends BaseModel {

    public static String STATE_DEFINED = "Cree";
    public static String STATE_ENDED = "Termine";

    @Column
    @PrimaryKey
    @Unique(uniqueGroups = 1)
    int contactId;

    @Column
    @Unique(uniqueGroups = 1)
    long campaignId;

    @Column
    String contactInfo;


    public ContactCampaign() {
    }

    public ContactCampaign(int contactId, long campaignId, String contactInfo) {
        this.contactId = contactId;
        this.campaignId = campaignId;
        this.contactInfo = contactInfo;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
