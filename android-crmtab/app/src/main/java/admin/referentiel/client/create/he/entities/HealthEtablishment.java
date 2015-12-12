package admin.referentiel.client.create.he.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.Table;

import pds.isintheair.fr.crm_tab.OrmTabDataBase;

/**
 * Created by tlacouque on 12/12/2015.
 */
@Table(databaseName = OrmTabDataBase.DBNAME)
public class HealthEtablishment extends Customer {


    @Column
    private boolean isPublic;

    @Column
    //@ForeignKey()
    EtablishmentType etablishmentType;


}
