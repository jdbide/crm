package tlacouque.uc.admin.ref.customer.model.asynctask;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;

import com.raizlabs.android.dbflow.config.FlowManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.io.File;
import java.lang.reflect.Field;

import fr.pds.isintheair.crmtab.BuildConfig;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.view.activity.LoginActivity;
import fr.pds.isintheair.crmtab.common.view.activity.MainActivity;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.asynctask.TileDownloader;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.entity.MapInfo;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.activity.CRUDCustomerActivity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by tlacouque on 29/02/2016.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, packageName = "pds.isintheair.fr.crmtab")
@RunWith(RobolectricGradleTestRunner.class)
public class TileDownloaderTest {

    TileDownloader td;
    LoginActivity activity;

    @Before
    public void setUp() throws Exception {
     //   activity = Robolectric.setupActivity(LoginActivity.class);
         td = new TileDownloader();
    }

    @Test
    public void testSaveImageOk() throws Exception {

        String PATH = Environment.getExternalStorageDirectory().toString()+"/test.png";
        Bitmap bitmap = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.logo);
        td.saveImage(bitmap,PATH);
        File file = new File(PATH);
        assertTrue(file.exists());
        file.delete();
    }

    @Test
    public void testDownloadBitmapOk() throws Exception {
        String PATH = "http://a.tile.openstreetmap.org/15/16595/11271.png";
        Bitmap bitmap = td.downloadBitmap(PATH);
        assertNotNull(bitmap);
    }
/**
    @Test
    public void testDoInBackground() throws Exception {
        MapInfo mapInfo = Mockito.mock(MapInfo.class);
        when(mapInfo.getX()).thenReturn(15);
        when(mapInfo.getY()).thenReturn(16595);
        when(mapInfo.getZ()).thenReturn(11271);
        td.execute(mapInfo);
        File file = new File("/");

    }*/

    @After
    public void tearDown() throws Exception {
        FlowManager.destroy();
    }
}
