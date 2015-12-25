package pds.isintheair.fr.crm_tab.client.create.he.test;

import org.junit.Test;

import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.fragment.CreateHCFragment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by tlacouque on 19/12/2015.
 */
public class CreateHCFragmentTest {



    @Test
    public void testSiretCalculTest() throws Exception {
        CreateHCFragment createHCFragment = CreateHCFragment.newInstance();
        assertTrue(createHCFragment.isSiretSyntaxValide("40483304800022"));
        assertFalse(createHCFragment.isSiretSyntaxValide("40483304800021"));
    }






}
