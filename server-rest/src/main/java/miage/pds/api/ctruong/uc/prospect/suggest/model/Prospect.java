package miage.pds.api.ctruong.uc.prospect.suggest.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

/**
 * The prospect model class
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
@Entity("healthcenter")
public class Prospect {

    @Id
    private int id;
    private String name;
    private long finessNumber;
    private long siretNumber;
    private long streetNumber;
    private long zipCode;
    private String address;
    private int place;
    private String website;

    /**
     *
     */
    public Prospect() {

    }

    /**
     * Full constructor of a prospect
     *
     * @param id
     * @param name
     * @param finessNumber
     * @param siretNumber
     * @param streetNumber
     * @param zipCode
     * @param address
     * @param place
     * @param website
     */
    public Prospect(int id, String name, long finessNumber, int siretNumber, int streetNumber, int zipCode, String address, int place, String website) {
        this.id = id;
        this.name = name;
        this.finessNumber = finessNumber;
        this.siretNumber = siretNumber;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.address = address;
        this.place = place;
        this.website = website;
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

    public long getFinessNumber() {
        return finessNumber;
    }

    public void setFinessNumber(long finessNumber) {
        this.finessNumber = finessNumber;
    }

    public long getSiretNumber() {
        return siretNumber;
    }

    public void setSiretNumber(long siretNumber) {
        this.siretNumber = siretNumber;
    }

    public long getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public long getZipCode() {
        return zipCode;
    }

    public void setZipCode(long zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * To String method
     *
     * @return JSON Object type String
     */
    @Override
    public String toString() {
        return "Prospect{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", finessNumber=" + finessNumber +
                ", siretNumber=" + siretNumber +
                ", streetNumber=" + streetNumber +
                ", zipCode=" + zipCode +
                ", address='" + address + '\'' +
                ", place=" + place +
                ", website='" + website + '\'' +
                '}';
    }


}
