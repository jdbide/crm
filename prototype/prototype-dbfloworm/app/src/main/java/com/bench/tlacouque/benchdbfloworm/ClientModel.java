package com.bench.tlacouque.benchdbfloworm;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by user on 19/11/2015.
 */
@Table(databaseName = ClientDataBase.DBNAME)
public class ClientModel extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    long id_client;

    @Column
    String name;

    @Column
    String firstname;

    @Column
    @ForeignKey(
            references = {@ForeignKeyReference(columnName = "client_etablissement_id",
                    columnType = Long.class,
                    foreignColumnName = "id")},
            saveForeignKeyModel = false)
    EtablissementModel etablissement;


    public void setId(long id) {
        this.id_client = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setEtablissement(EtablissementModel etablissement) {
        this.etablissement = etablissement;
    }
}
