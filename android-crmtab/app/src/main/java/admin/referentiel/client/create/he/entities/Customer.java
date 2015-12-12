package admin.referentiel.client.create.he.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import pds.isintheair.fr.crm_tab.OrmTabDataBase;

/**
 * Created by tlacouque on 12/12/2015.
 */
@Table(databaseName = OrmTabDataBase.DBNAME)
public abstract class Customer extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    private String name;

    @Column
    private int siretNumber;

    @Column
    private int finessNumber;

    @Column
    private int streetNumber;

    @Column
    private String streetName;

    @Column
    private String town;

    @Column
    private int zipCode;

    @Column
    private double longitude;

    @Column
    private double lattitude;

    @Column
    private String webSite;

    @Column
    private int serviceBuildingImage;

    @Column
    private String origin;


    public Customer() {
    }

    public Customer(int id, String name, boolean isPublic, int siretNumber, int finessNumber,
                    int streetNumber, String streetName, String town, int zipCode, double longitude,
                    double lattitude, String webSite, int serviceBuildingImage, String origin) {
        this.id = id;
        this.name = name;
        this.siretNumber = siretNumber;
        this.finessNumber = finessNumber;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.town = town;
        this.zipCode = zipCode;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.webSite = webSite;
        this.serviceBuildingImage = serviceBuildingImage;
        this.origin = origin;
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

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public int getServiceBuildingImage() {
        return serviceBuildingImage;
    }

    public void setServiceBuildingImage(int serviceBuildingImage) {
        this.serviceBuildingImage = serviceBuildingImage;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
