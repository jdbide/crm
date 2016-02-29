package tlacouque.uc.admin.ref.customer.model.asynctask;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.File;

import fr.pds.isintheair.crmtab.BuildConfig;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.asynctask.TileDownloader;

import static org.junit.Assert.assertTrue;

/**
 * Created by tlacouque on 29/02/2016.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, packageName = "pds.isintheair.fr.crmtab")
@RunWith(RobolectricGradleTestRunner.class)
public class TileDownloaderTest {

    @Test
    public void testSaveImage() throws Exception {
        TileDownloader td = new TileDownloader();
        String PATH = Environment.getExternalStorageDirectory().toString()+"/test.png";
        Bitmap bitmap = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.logo);
        td.saveImage(bitmap,PATH);
        File file = new File(PATH);
        assertTrue(file.exists());
        file.delete();
    }


}
