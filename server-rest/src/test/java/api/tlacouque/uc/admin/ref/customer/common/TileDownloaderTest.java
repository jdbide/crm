package api.tlacouque.uc.admin.ref.customer.common;

import miage.pds.api.tlacouque.uc.admin.ref.customer.common.TileDownloader;
import miage.pds.api.tlacouque.uc.admin.ref.customer.entities.MapInfo;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

/**
 * Created by tlacouque on 02/02/2016.
 */
public class TileDownloaderTest {


    @Test
    public void testdwdTile() throws Exception {
        MapInfo mapInfo = Mockito.mock(MapInfo.class);
        when(mapInfo.getX()).thenReturn(15);
        when(mapInfo.getY()).thenReturn(16597);
        when(mapInfo.getZ()).thenReturn(11270);
        TileDownloader.dwdTile(mapInfo);

    }
}
