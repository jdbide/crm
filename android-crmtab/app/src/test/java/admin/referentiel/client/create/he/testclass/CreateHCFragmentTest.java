package admin.referentiel.client.create.he.testclass;



import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import org.junit.Test;

import admin.referentiel.client.create.he.fragment.CreateHCFragment;

/**
 * Created by tlacouque on 17/12/2015.
 */
public class CreateHCFragmentTest extends ActivityInstrumentationTestCase2<FragmentContainerActivity> {

    CreateHCFragment createHCFragment;

    public CreateHCFragmentTest() {
        super(FragmentContainerActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createHCFragment = new CreateHCFragment();
       // getActivity().addFragment(createHCFragment, CreateHCFragment.class.getSimpleName());
       // getInstrumentation().waitForIdleSync();

    }


    @Test
    public void testSiret() {
        assertEquals(true,createHCFragment.isSiretSyntaxValide("40483304800022"));
        assertEquals(false,createHCFragment.isSiretSyntaxValide("40483304800021"));

    }




}
