package miage.pds.admin.customer.crud.createhc.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by tlacouque on 16/12/2015.
 */

@Entity("healthcenter")
public class HealthCenter {

    @Id
    int id;

    @Property
    String name;

    @Property
    long  siretNumber;

    @Property
    long  finessNumber;

    @Property
    int streetNumber;

    @Property
    String streetName;

    @Property
    String town;

    @Property
    int zipCode;

    @Property
    double longitude;

    @Property
    double lattitude;

    @Property
    int bedNumber;

    @Property
    String webSite;


    @Property
    int serviceBuildingImage;

    @Property
    int difficultyHavingContact;


    @Property
    String origin;


    @Property
    boolean isPublic;

    @Reference
    EtablishmentType etablishmentType;

    @Reference
    PurchasingCentral purchasingCentral;

    @Reference
    Holding holding;


    public HealthCenter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getName() {
        return name;
    }

   // @Override
    public String getAdress() {
        return ""+streetNumber+" "+streetName+" ,"+town;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSiretNumber() {
        return siretNumber;
    }

    public void setSiretNumber(long siretNumber) {
        this.siretNumber = siretNumber;
    }

    public long  getFinessNumber() {
        return finessNumber;
    }

    public void setFinessNumber(long finessNumber) {
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


    public boolean isPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public EtablishmentType getEtablishmentType() {
        return etablishmentType;
    }

    public void setEtablishmentType(EtablishmentType etablishmentType) {
        this.etablishmentType = etablishmentType;
    }

    public PurchasingCentral getPurchasingCentral() {
        return purchasingCentral;
    }

    public void setPurchasingCentral(PurchasingCentral purchasingCentral) {
        this.purchasingCentral = purchasingCentral;
    }

    public Holding getHolding() {
        return holding;
    }

    public void setHolding(Holding holding) {
        this.holding = holding;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public int getDifficultyHavingContact() {
        return difficultyHavingContact;
    }

    public void setDifficultyHavingContact(int difficultyHavingContact) {
        this.difficultyHavingContact = difficultyHavingContact;
    }
}
