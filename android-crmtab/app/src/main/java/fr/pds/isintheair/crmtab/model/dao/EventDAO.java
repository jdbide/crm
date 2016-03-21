package fr.pds.isintheair.crmtab.model.dao;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import fr.pds.isintheair.crmtab.model.entity.Event;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 03/20/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class EventDAO {
    public static List<Event> getAll() {
        return new Select().from(Event.class).queryList();
    }
}
