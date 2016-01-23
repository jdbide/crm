package miage.pds;

import javax.print.DocFlavor;

/**
 * Created by tlacouque on 21/01/2016.
 *
 */

public interface MongoConfig {

    // IP + PORT Adress Prod instance
    public static String PROD_IP = "192.168.20.3";
    public static int PROD_PORT = 8086;

    // IP + PORT Adress Pre Prod instancce
    public static String PRE_PROD_IP = "192.168.20.3";
    public static int PRE_PROD_PORT = 8061;

    // IP + PORT Adress Dev instancce
    public static String DEV_IP = "192.168.20.3";
    public static int DEV_PORT = 8071;

    //IP + PORT Adress localhost instance
    // IP + PORT Adress Dev instancce
    public static String LOCAL_IP = "127.0.0.1";
    public static int LOCAL_PORT = 27017;
}
