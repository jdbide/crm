package fr.pds.isintheair.crmtab.jdatour.wi.agenda.mock.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Calendar;

import fr.pds.isintheair.crmtab.common.model.database.OrmTabDataBase;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 03/14/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

@Table(database = OrmTabDataBase.class)
public class Event extends BaseModel {
    @PrimaryKey
    @Column
    Long id;

    @Column
    String description;

    @Column
    Calendar endTime;

    @Column
    Calendar startTime;

    @Column
    String title;

    @Column
    String where;

    public Event() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
}
