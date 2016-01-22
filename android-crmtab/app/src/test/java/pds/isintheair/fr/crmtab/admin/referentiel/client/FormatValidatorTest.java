package pds.isintheair.fr.crmtab.admin.referentiel.client;

import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import fr.pds.isintheair.crmtab.BuildConfig;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.FormatValidator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by tlacouque on 19/01/2016.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, packageName = "pds.isintheair.fr.crmtab")
@RunWith(RobolectricGradleTestRunner.class)
public class FormatValidatorTest {

    @Test
    public void testFormatUrl() throws Exception {
        String urlWithoutHTTP = "www.url.com";
        String http = "http://";

        assertEquals(http+urlWithoutHTTP, FormatValidator.formatUrl(urlWithoutHTTP));
        assertNotEquals(urlWithoutHTTP, FormatValidator.formatUrl(urlWithoutHTTP));
        assertEquals(http+urlWithoutHTTP,FormatValidator.formatUrl(http+urlWithoutHTTP));
    }

    @Test
    public void testisSiretSyntaxValide() throws Exception {

        final String siretNumberValid = "26770008600056";
        final String siretNumberNotValid = "2677000860005";

        assertTrue(FormatValidator.isSiretSyntaxValide(siretNumberValid));
        assertFalse(FormatValidator.isSiretSyntaxValide(siretNumberNotValid));

    }
}
