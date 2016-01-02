package pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.enums;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import pds.isintheair.fr.crm_tab.OrmTabDataBase;

/**
 * Created by tlacouque on 12/12/2015.
 * Enum used to stock etablishment type
 */

public enum EtablishmentType {
    CENTRE_HOSPITALIER_UNIVERSITAIRE("Centre hospitalier universitaire (CHU)"),
    CENTRE_HOSPITALIER("Centre hospitalier (CH)"),
    CLINIQUE("Clinique"),
    CENTRE_LUTTE_CONTRE_CANCER("Centres de lutte contre le cancer (CLCC)"),
    HOPITAL_LOCAL("Hôpital local (HL)"),
    CENTRE_PSYCHIATRIQUE("Centre hospitalier spécialisé en psychiatrie (CHS)"),
    CENTRE_HOSPITALIER_REGIONAL("Centre hospitalier régional (CHR)"),
    ETABLISSEMNT_HEBERGEMENT_PERSONNE_AGEE_DEPENDANTE
            ("Établissement d'hébergement pour personnes âgées dépendantes (EHPAD)"),
    ETABLISSEMNT_HEBERGEMENT_PERSONNE_AGEE("Établissement d’hébergement pour personnes âgées (EHPA)");


    private String stringValue;

    private EtablishmentType(String toString) {
        stringValue = toString;
    }

    /**
     * Method implemented to call Enum.attribute.toString(). Return string of this attribute.
     * @return
     */
    @Override
    public String toString() {
        return stringValue;
    }
}
