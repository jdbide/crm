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

    public long getSiretNumber() {
        return siretNumber;
    }

    public void setSiretNumber(long siretNumber) {
        this.siretNumber = siretNumber;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}


