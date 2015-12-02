package com.bench.tlacouque.benchormandroid;

import com.orm.SugarRecord;

/**
 * Created by user on 20/11/2015.
 */
public class EtablissementSugarORM extends SugarRecord<EtablissementSugarORM> {

    private int id;
    private String nom;


    public EtablissementSugarORM() {
    }

    public EtablissementSugarORM(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

}
