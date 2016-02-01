package miage.pds.api.tlacouque.uc.admin.ref.customer.entities;

import org.mongodb.morphia.annotations.Entity;

/**
 * Created by tlacouque on 01/02/2016.
 */

@Entity("mapinfo")
public class MapInfo {
    long siretNumber;
    int x;
    double y;
    double  z;


    public MapInfo(long siretNumber, int x, double y, double z) {
        this.siretNumber = siretNumber;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
