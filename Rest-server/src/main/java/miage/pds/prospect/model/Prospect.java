package miage.pds.prospect.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by Truong on 12/20/2015.
 * @version 1.1
 * @since 20/12/2015
 * @see Prospect model
 */

@Document(collection = "client")
public class Prospect {

    @Id
    private Integer id;

    @Field("name")
    private String name;

    @Field("finess")
    private int finessNumber;

    @Field("siret")
    private int siretNumber;

    @Field("streetNumber")
    private int streetNumber;

    @Field("zipCode")
    private int zipCode;

    @Field("address")
    private String address;

    @Field("city")
    private String city;

    @Field("place")
    private int place;

    @Field("website")
    private String website;

    public Prospect(String name, int place) {
        setName(name);
        setPlace(place);
    }

    /**
     * Constructor of the Prospect model
     * @param name
     * @param finessNumber
     * @param siretNumber
     * @param streetNumber
     * @param zipCode
     * @param address
     * @param city
     * @param place
     * @param website
     */
    public Prospect(String name, int finessNumber, int siretNumber, int streetNumber, int zipCode, String address, String city, int place, String website) {
        setName(name);
        setFinessNumber(finessNumber);
        setSiretNumber(siretNumber);
        setStreetNumber(streetNumber);
        setZipCode(zipCode);
        setAddress(address);
        setCity(city);
        setPlace(place);
        setWebsite(website);
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    @Override
    public String toString() {
        return "Prospect{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", finessNumber=" + finessNumber +
                ", siretNumber=" + siretNumber +
                ", streetNumber=" + streetNumber +
                ", zipCode=" + zipCode +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", place=" + place +
                ", website='" + website + '\'' +
                '}';
    }
}
