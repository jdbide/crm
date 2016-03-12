package tlacouque.uc.admin.ref.customer;

import android.os.Environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.FormatValidator;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.MapInfo;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.MapUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Created by tlacouque on 12/03/2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Environment.class, File.class}) // Prepare the static classes for mocking
public class MapUtilsTest {

    @Mock
    File file;


    @Test
    public void testIsTileFileSavedOnDeviceOk() throws Exception {
        MapInfo mapInfo = Mockito.mock(MapInfo.class);
        when(mapInfo.getX()).thenReturn(15);
        when(mapInfo.getY()).thenReturn(16595);
        when(mapInfo.getZ()).thenReturn(11271);

        // Setup mocking for Environment and File classes
        mockStatic(Environment.class, File.class);


        // Make the Environment class return a mocked external storage directory
        when(Environment.getExternalStorageDirectory())
                .thenReturn(file);
        when(file.toString()).thenReturn("");

        String string = FormatValidator.formatPathTile(mapInfo);
        File fakeMapInfo = new File(string);
        fakeMapInfo.getParentFile().mkdirs();
        fakeMapInfo.createNewFile();
        assertTrue(MapUtils.isTileFileSavedOnDevice(mapInfo));
        fakeMapInfo.delete();
       // fakeMapInfo.getParentFile().delete()
    }

    @Test
    public void testIsTileFileSavedOnDeviceNok() throws Exception {
        MapInfo mapInfoFile = Mockito.mock(MapInfo.class);
        when(mapInfoFile.getX()).thenReturn(15);
        when(mapInfoFile.getY()).thenReturn(16595);
        when(mapInfoFile.getZ()).thenReturn(11271);

        MapInfo mapInfoTile = Mockito.mock(MapInfo.class);
        when(mapInfoFile.getX()).thenReturn(15);
        when(mapInfoFile.getY()).thenReturn(16595);
        when(mapInfoFile.getZ()).thenReturn(11272);

        // Setup mocking for Environment and File classes
        mockStatic(Environment.class, File.class);


        // Make the Environment class return a mocked external storage directory
        when(Environment.getExternalStorageDirectory())
                .thenReturn(file);
        when(file.toString()).thenReturn("");

        String string = FormatValidator.formatPathTile(mapInfoFile);
        File fakeMapInfo = new File(string);
        fakeMapInfo.getParentFile().mkdirs();
        fakeMapInfo.createNewFile();
        assertFalse(MapUtils.isTileFileSavedOnDevice(mapInfoTile));
        fakeMapInfo.delete();
        // fakeMapInfo.getParentFile().delete()
    }

}
