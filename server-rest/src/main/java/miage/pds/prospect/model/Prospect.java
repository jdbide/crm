package miage.pds.prospect.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by Truong on 12/20/2015.
 * @version 1.1
 * @since 20/12/2015
 * @see Prospect model
 */
@Entity("client")
public class Prospect {

    @Id
    private ObjectId objectId;
    private int id;
    private String name;
    private int finessNumber;
    private int siretNumber;
    private int streetNumber;
    private int zipCode;
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
    public Prospect(int id, String name, int finessNumber, int siretNumber, int streetNumber, int zipCode, String address, int place, String website) {
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

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
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

    public int getFinessNumber() {
        return finessNumber;
    }

    public void setFinessNumber(int finessNumber) {
        this.finessNumber = finessNumber;
    }

    public int getSiretNumber() {
        return siretNumber;
    }

    public void setSiretNumber(int siretNumber) {
        this.siretNumber = siretNumber;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
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
     * @return JSON Object type String
     */
    @Override
    public String toString() {
        return "Prospect{" +
                "objectId=" + objectId +
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
