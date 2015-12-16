package fr.datour.protoclientrest.model.orm;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = ProtoClientRESTDatabase.NAME, version = ProtoClientRESTDatabase.VERSION)
public class ProtoClientRESTDatabase {
    public static final String NAME    = "proto_client_rest";
    public static final int    VERSION = 1;
}
