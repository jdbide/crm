package pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.enums;

/**
 * Created by tlacouque on 12/12/2015.
 */
public enum CustomerType {

    HEALTH_ETABLISHMENT ("Etablissement de santé"),
    INDEPENDENT ("Indépendant");


    private String stringValue;

    private CustomerType(String toString) {
        stringValue = toString;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
