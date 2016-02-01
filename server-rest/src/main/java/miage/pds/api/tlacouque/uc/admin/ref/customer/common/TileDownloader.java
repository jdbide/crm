package miage.pds.api.tlacouque.uc.admin.ref.customer.common;

import miage.pds.api.tlacouque.uc.admin.ref.customer.entities.MapInfo;

import java.io.*;
import java.net.URL;

/**
 * Created by tlacouque on 01/02/2016.
 */
public class TileDownloader {

    private static String URL_BASE = "http://tile.openstreetmap.org/";

    public static boolean dwdTile(MapInfo mapInfo) {
        String url = formatUrl(mapInfo);
        String imageUrl = URL_BASE+url;
        String destinationFile = System.getProperty("catalina.base")+"/webapps/image/"+url;
        new File(destinationFile).mkdirs();

        try {
            saveImage(imageUrl, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }


    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }


    public static String formatUrl(MapInfo mapInfo) {
        return mapInfo.getX() + "/" + "/" + mapInfo.getY()+"/"+mapInfo.getZ()+".png";
    }
}
