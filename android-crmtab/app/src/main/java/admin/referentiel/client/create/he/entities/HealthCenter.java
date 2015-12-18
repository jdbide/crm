package admin.referentiel.client.create.he.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import pds.isintheair.fr.crm_tab.OrmTabDataBase;

/**
 * Created by tlacouque on 12/12/2015.
 */
@Table(databaseName = OrmTabDataBase.DBNAME)
public class HealthCenter extends BaseModel implements Customer{

    @Column
    @PrimaryKey(autoincrement = true)
    int id;

    @Column
    String name;

    @Column
    long  siretNumber;

    @Column
    long  finessNumber;

    @Column
    int streetNumber;

    @Column
    String streetName;

    @Column
    String town;

    @Column
    int zipCode;

    @Column
    double longitude;

    @Column
    double lattitude;

    @Column
    int bedNumber;

    @Column
    String webSite;

    @Column
    int serviceBuildingImage;

    @Column
    int difficultyHavingContact;



    @Column
    String origin;


    @Column
    boolean isPublic;

    @Column
    @ForeignKey(
            references = {@ForeignKeyReference(columnName = "etablissement_type_id",
                    columnType = int.class,
                    foreignColumnName = "id")},
            saveForeignKeyModel = false)
    EtablishmentType etablishmentType;


    @Column
    @ForeignKey(
            references = {@ForeignKeyReference(columnName = "holding_id",
                    columnType = int.class,
                    foreignColumnName = "id")},
            saveForeignKeyModel = false)
    PurchasingCentral purchasingCentral;


    @Column
    @ForeignKey(
            references = {@ForeignKeyReference(columnName = "purchasing_central_id",
                    columnType = int.class,
                    foreignColumnName = "id")},
            saveForeignKeyModel = false)
    Holding holding;





    public HealthCenter() {
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

    @Override
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
