package pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import pds.isintheair.fr.crm_tab.OrmTabDataBase;

/**
 * Created by tlacouque on 12/12/2015.
 */
@Table(databaseName = OrmTabDataBase.DBNAME)
public class HealthCenter extends BaseModel {

    @Column
    @PrimaryKey
    long  siretNumber;

    @Column
    String name;

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
    String etablishmentType;

    @Column
    int purchasingCentralId;

    @Column
    int holdingId;





    public HealthCenter() {
    }


    public String getName() {
        return name;
    }


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

    public String getEtablishmentType() {
        return etablishmentType;
    }

    public void setEtablishmentType(String etablishmentType) {
        this.etablishmentType = etablishmentType;
    }

    public int getPurchasingCentralId() {
        return purchasingCentralId;
    }

    public void setPurchasingCentralId(int purchasingCentralId) {
        this.purchasingCentralId = purchasingCentralId;
    }

    public int getHoldingId() {
        return holdingId;
    }

    public void setHoldingId(int holdingId) {
        this.holdingId = holdingId;
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

    public Holding getHolding() {
        return new Select().from(Holding.class).where(Condition.column(Holding$Table.ID).eq(holdingId)).querySingle();
    }

    public PurchasingCentral getPurchasingCentral() {
        return new Select().from(PurchasingCentral.class)
                .where(Condition.column(PurchasingCentral$Table.ID).eq(purchasingCentralId)).querySingle();
    }
}
