package miage.pds.api;

import javax.print.DocFlavor;

/**
 * Created by tlacouque on 21/01/2016.
 *
 */

public interface MongoConfig {

    // IP + PORT Adress Prod instancce
    public static String PROD_MONGO_IP = "192.168.20.3";
    public static int PROD_MONGO_PORT = 8086;

    // IP + PORT Adress Pre Prod instancce
    public static String PRE_PROD_MONGO_IP = "192.168.20.3";
    public static int PRE_PROD_MONGO_PORT = 27017;

    // IP + PORT Adress Dev instancce
    public static String DEV_MONGO_IP = "192.168.20.3";
    public static int DEV_PORT = 8071;

    //IP + PORT Adress localhost instance
    // IP + PORT Adress Dev instancce
    public static String LOCAL_MONGO_IP = "127.0.0.1";
    public static int LOCAL_PORT = 27017;
}
