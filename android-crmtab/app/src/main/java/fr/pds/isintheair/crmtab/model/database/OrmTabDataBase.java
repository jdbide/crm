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

    public static final String AUTHORITY = "fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.provider";

    public static final String BASE_CONTENT_URI = "content://";

    private static Uri buildUri(String... paths) {
        Uri.Builder builder = Uri.parse(OrmTabDataBase.BASE_CONTENT_URI + OrmTabDataBase.AUTHORITY).buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }
}
