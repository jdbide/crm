package pds.isintheair.fr.crm_tab.isin.truong.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import pds.isintheair.fr.crm_tab.OrmTabDataBase;

/**
 * Created by Truong on 12/14/2015.
 */
@Table(databaseName = OrmTabDataBase.DBNAME)
public class Prospect extends BaseModel implements Parcelable {
    @Column
    @PrimaryKey
    private int id;
    @Column
    private String name;
    @Column
    private int streetNumber;
    @Column
    private String address;
    @Column
    private int zipCode;
    @Column
    private String city;
    @Column
    private String origin;
    @Column
    private String website;
    @Column
    private int levelContact;
    @Column
    private int place;
    @Column
    private int siretNumber;
    @Column
    private int finessNumber;
    private boolean isPublic;
    @Column
    private double visiteFrequency;
    private List<Sale> sales;

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    /**
     * Constructor
     */
    public Prospect() {
    }

    /**
     * Constructor with Parcel
     *
     * @param in
     */
    protected Prospect(Parcel in) {
        name = in.readString();
        address = in.readString();
        city = in.readString();
        website = in.readString();
        origin = in.readString();
        id = in.readInt();
        streetNumber = in.readInt();
        zipCode = in.readInt();
        finessNumber = in.readInt();
        siretNumber = in.readInt();
        place = in.readInt();
        levelContact = in.readInt();
        isPublic = in.readByte() != 0;
        visiteFrequency = in.readDouble();
    }

    /**
     * Parcelable
     */
    public static final Creator<Prospect> CREATOR = new Creator<Prospect>() {
        @Override
        public Prospect createFromParcel(Parcel in) {
            return new Prospect(in);
        }

        @Override
        public Prospect[] newArray(int size) {
            return new Prospect[size];
        }
    };

    /**
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Write Parcel to send
     *
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(website);
        dest.writeString(origin);
        dest.writeInt(id);
        dest.writeInt(streetNumber);
        dest.writeInt(zipCode);
        dest.writeInt(finessNumber);
        dest.writeInt(siretNumber);
        dest.writeInt(place);
        dest.writeInt(levelContact);
        dest.writeByte((byte) (isPublic ? 1 : 0));
        dest.writeDouble(visiteFrequency);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getLevelContact() {
        return levelContact;
    }

    public void setLevelContact(int levelContact) {
        this.levelContact = levelContact;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getSiretNumber() {
        return siretNumber;
    }

    public void setSiretNumber(int siretNumber) {
        this.siretNumber = siretNumber;
    }

    public int getFinessNumber() {
        return finessNumber;
    }

    public void setFinessNumber(int finessNumber) {
        this.finessNumber = finessNumber;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }


    public double getVisiteFrequency() {
        return visiteFrequency;
    }

    public void setVisiteFrequency(double visiteFrequency) {
        this.visiteFrequency = visiteFrequency;
    }
}
