package admin.referentiel.client.create.he.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import pds.isintheair.fr.crm_tab.OrmTabDataBase;

/**
 * Created by tlacouque on 12/12/2015.
 */


public interface Customer  {
    String getName();
    String getAdress();

}
