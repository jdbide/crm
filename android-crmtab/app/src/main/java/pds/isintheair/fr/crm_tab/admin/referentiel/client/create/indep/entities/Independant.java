package pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import pds.isintheair.fr.crm_tab.OrmTabDataBase;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.Customer;

/**
 * Created by tlacouque on 27/12/2015.
 */
@Table(databaseName = OrmTabDataBase.DBNAME)
public class Independant extends BaseModel implements Customer {

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
    String webSite;

    @Column
    int longTermFidelity;

    @Column
    String origin;

    @Column
    String independantType;

    @Column
    int specialtyId;

    @Column
    int companyId;



    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAdress() {
        return ""+streetNumber+" "+streetName+" ,"+town;
    }

    public long getSiretNumber() {
        return siretNumber;
    }

    public void setSiretNumber(long siretNumber) {
        this.siretNumber = siretNumber;
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

    public int getLongTermFidelity() {
        return longTermFidelity;
    }

    public void setLongTermFidelity(int longTermFidelity) {
        this.longTermFidelity = longTermFidelity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getIndependantType() {
        return independantType;
    }

    public void setIndependantType(String intdependantType) {
        this.independantType = intdependantType;
    }

    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
