package pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.enums;

/**
 * Created by tlacouque on 27/12/2015.
 */
public enum IndependantType {

    MEDECIN_GENERALISTE_LIBERAL("Médecin généraliste libérale"),
    INFIRMIERE_LIBERAL("Infirmière libéral"),
    SPECIALISTE("Spécialiste"),
    PHARMACIE("Pharmacie");


    private String stringValue;

    private IndependantType(String toString) {
        stringValue = toString;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
