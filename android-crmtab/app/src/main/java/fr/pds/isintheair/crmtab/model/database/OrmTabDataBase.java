package fr.pds.isintheair.crmtab.model.database;

import android.net.Uri;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.provider.ContentProvider;

/**
 * Created by user on 08/12/2015.
 */
@ContentProvider(authority = OrmTabDataBase.AUTHORITY,
        database = OrmTabDataBase.class,
        baseContentUri = OrmTabDataBase.BASE_CONTENT_URI)
@Database(name = OrmTabDataBase.DBNAME, version = OrmTabDataBase.DBVERSION)
public class OrmTabDataBase {
    public static final String DBNAME = "CRMTabDataBase";
    public static final int DBVERSION = 1;

    // Define the authority of the provider
    public static final String AUTHORITY = "fr.pds.isintheair.crm.controller.ctruong.uc.propsect.suggestion.provider";

    public static final String BASE_CONTENT_URI = "content://";



}
