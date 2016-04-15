package fr.pds.isintheair.crmtab;

import android.os.Build;

import com.raizlabs.android.dbflow.config.FlowManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import fr.pds.isintheair.crmtab.model.dao.ContactCampaignDAO;
import fr.pds.isintheair.crmtab.model.entity.ContactCampaign;

import static org.junit.Assert.assertNotNull;

/**
 * Created by tlacouque on 12/04/2016.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class ContactCampaignDAOTest
{
    ContactCampaign cc;
    @Before
    public void setUp() throws Exception {
        cc = new ContactCampaign(1,2L,"");
        cc.setStatus(ContactCampaign.STATE_DEFINED);
        cc.save();
    }

    @Test
    public void testGetContactCampaignFromIds() throws Exception {
        ContactCampaign cp = ContactCampaignDAO.getContactCampaignFromIds(1, 2L);
        assertNotNull(cp);
    }

    @Test
    public void testGetContactCampaignFromCampaignId() throws Exception {
        assertNotNull(ContactCampaignDAO.getContactCampaignFromCampaignId(2L));
    }

    @After
    public void tearDown() throws Exception {
        cc.delete();
        FlowManager.destroy();
    }
}
