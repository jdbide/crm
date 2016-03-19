package tlacouque.uc.admin.ref.customer;

import android.os.Environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

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
/*        MapInfo mapInfo = Mockito.mock(MapInfo.class);
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
        fakeMapInfo.delete(); */

    }

    @Test
    public void testIsTileFileSavedOnDeviceNok() throws Exception {
 /*       MapInfo mapInfoFile = Mockito.mock(MapInfo.class);
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
        fakeMapInfo.delete(); */
    }

    // This test and other on isTileSavedOnDeviceOk() are not done because of some unexpected error
    // on dbflow library

    /**
     @Test public void testisTileSavedOnDeviceOk() throws Exception {

     MapInfo mapInfoFile = Mockito.mock(MapInfo.class);
     when(mapInfoFile.getX()).thenReturn(15);
     when(mapInfoFile.getY()).thenReturn(16595);
     when(mapInfoFile.getZ()).thenReturn(11271);


     // Setup mocking for Environment and File classes
     mockStatic(Environment.class, File.class, MapInfoDAO.class);

     PowerMockito.when(MapInfoDAO.getMapInfo(mapInfoFile.getSiretNumber()))
     .thenReturn(mapInfoFile);

     PowerMockito.when(MapInfoDAO.isMapInfoExist(mapInfoFile.getSiretNumber()))
     .thenReturn(true);




     // Make the Environment class return a mocked external storage directory
     when(Environment.getExternalStorageDirectory())
     .thenReturn(file);
     when(file.toString()).thenReturn("");

     String string = FormatValidator.formatPathTile(mapInfoFile);
     File fakeMapInfo = new File(string);
     fakeMapInfo.getParentFile().mkdirs();
     fakeMapInfo.createNewFile();
     assertTrue(MapUtils.isTileSavedOnDevice(1L));
     fakeMapInfo.delete();

     }


     @After public void tearDown() throws Exception {
     FlowManager.destroy();
     }
     */
}
