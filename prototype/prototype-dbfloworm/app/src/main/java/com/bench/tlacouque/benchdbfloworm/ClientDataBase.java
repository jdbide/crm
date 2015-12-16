package com.bench.tlacouque.benchdbfloworm;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by user on 19/11/2015.
 */

@Database(name = ClientDataBase.DBNAME, version = ClientDataBase.DBVERSION)
public class ClientDataBase {
    public static final String DBNAME = "ClientDbFlow";
    public static final int DBVERSION = 3;
}


