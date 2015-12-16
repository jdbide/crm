package com.bench.tlacouque.benchormandroid;

import com.orm.SugarRecord;

/**
 * Created by user on 19/11/2015.
 */
public class ClientSugarORM extends SugarRecord<ClientSugarORM> {

    private int id;
    private String nom;
    private String prenom;
    private String etablissement;

    public ClientSugarORM() {
    }

    public ClientSugarORM(int id, String nom, String prenom, String etablissement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.etablissement = etablissement;
    }

}
