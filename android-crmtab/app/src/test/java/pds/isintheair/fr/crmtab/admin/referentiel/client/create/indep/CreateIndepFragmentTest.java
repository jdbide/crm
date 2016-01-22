package pds.isintheair.fr.crmtab.admin.referentiel.client.create.indep;

import android.app.AlertDialog;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.raizlabs.android.dbflow.config.FlowManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;

import java.lang.reflect.Field;

import fr.pds.isintheair.crmtab.BuildConfig;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.activity.CRUDCustomerActivity;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.create.indep.entities.Company;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.create.indep.entities.Independant;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.create.indep.entities.Specialty;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.create.indep.fragment.CreateIndepFragment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by tlacouque on 19/01/2016.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, packageName = "pds.isintheair.fr.crmtab")
@RunWith(RobolectricGradleTestRunner.class)
public class CreateIndepFragmentTest {
    private CRUDCustomerActivity activity;
    private Specialty specialtyAucune;
    Specialty specialtyCardio;
    Company companyAucune;
    Company  companyDenfert;



    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(CRUDCustomerActivity.class);
        initDb();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                activity.onPositiveClick(1);
            }
        });

    }

    @Test
    public void testUISpinerInit() throws Exception {
        Spinner spinner = (Spinner) activity.findViewById(R.id.create_indep_fragment_independant_type);
        assertEquals(spinner.getAdapter().getCount(),6);
        Spinner spinnerCompany = (Spinner) activity.findViewById(R.id.create_indep_fragment_company);
        assertEquals(2, spinnerCompany.getAdapter().getCount());
        Spinner spinnerSpecialty = (Spinner) activity.findViewById(R.id.create_indep_fragment_specialty);
        assertEquals(2, spinnerSpecialty.getAdapter().getCount());

    }

    @Test
    public void testOnValidationSucceedded() throws Exception {
        // Mockito.verify(CustomerService.class).;
        // TODO: 19/01/2016

    }

    @Test
    public void testInitIndepAndInitSpinner() throws Exception {

        final String nameTest = "testName";
        final String siretNumberTest = "26770008600056";
        final String finessNumberTest = "234735732";
        final String streetNumberTest = "1";
        final String streetNameTest = "rue de chalautre";
        final String townNameTest = "Provins";
        final String zipCodeTest = "77160";
        final String webSiteNameTest = "www.test.com";
        EditText name = (EditText) activity.findViewById(R.id.create_indep_fragment_name);
        name.setText(nameTest);
        EditText siretNumber = (EditText) activity.findViewById(R.id.create_indep_fragment_siret_number);
        EditText finessNumber = (EditText) activity.findViewById(R.id.create_indep_fragment_finess_number);
        EditText streetNumber = (EditText) activity.findViewById(R.id.create_indep_fragment_street_number);
        EditText streetName = (EditText) activity.findViewById(R.id.create_indep_fragment_street_name);
        EditText townName = (EditText) activity.findViewById(R.id.create_indep_fragment_town_name);
        EditText zipCode = (EditText) activity.findViewById(R.id.create_indep_fragment_zip_code);
        EditText webSite = (EditText) activity.findViewById(R.id.create_indep_fragment_web_site);
        siretNumber.setText(siretNumberTest);
        finessNumber.setText(finessNumberTest);
        streetNumber.setText(streetNumberTest);
        streetName.setText(streetNameTest);
        townName.setText(townNameTest);
        zipCode.setText(zipCodeTest);
        webSite.setText(webSiteNameTest);

        CreateIndepFragment createIndepFragment =
                (CreateIndepFragment) activity.getFragmentManager().findFragmentById(R.id.create_customer_fragment_container);

        Independant independant = createIndepFragment.initIndep();


        assertEquals(nameTest, independant.getName());
        assertEquals(Long.valueOf(finessNumberTest).longValue(), independant.getFinessNumber());
        assertEquals(Long.valueOf(siretNumberTest).longValue(), independant.getSiretNumber());
        assertEquals(Integer.decode(streetNumberTest).intValue(),independant.getStreetNumber());
        assertEquals(streetNameTest,independant.getStreetName());
        assertEquals(townNameTest,independant.getTown());
        assertEquals(Integer.decode(zipCodeTest).intValue(),independant.getZipCode());
        assertEquals(webSiteNameTest,independant.getWebSite());
        assertEquals(0,independant.getSpecialtyId());
        assertEquals(1,independant.getCompanyId());

    }


    @Test
    public void testOnValidationFailed() throws Exception {
        final String nameTest = "";
        final String siretNumberTest = "2677000860005";
        final String finessNumberTest = "23473573";
        final String streetNumberTest = "";
        final String streetNameTest = "";
        final String townNameTest = "";
        final String zipCodeTest = "7716";
        EditText name = (EditText) activity.findViewById(R.id.create_indep_fragment_name);
        name.setText(nameTest);
        EditText siretNumber = (EditText) activity.findViewById(R.id.create_indep_fragment_siret_number);
        EditText finessNumber = (EditText) activity.findViewById(R.id.create_indep_fragment_finess_number);
        EditText streetNumber = (EditText) activity.findViewById(R.id.create_indep_fragment_street_number);
        EditText streetName = (EditText) activity.findViewById(R.id.create_indep_fragment_street_name);
        EditText townName = (EditText) activity.findViewById(R.id.create_indep_fragment_town_name);
        EditText zipCode = (EditText) activity.findViewById(R.id.create_indep_fragment_zip_code);
        siretNumber.setText(siretNumberTest);
        finessNumber.setText(finessNumberTest);
        streetNumber.setText(streetNumberTest);
        streetName.setText(streetNameTest);
        townName.setText(townNameTest);
        zipCode.setText(zipCodeTest);
        Button button = (Button) activity.findViewById(R.id.create_indep_fragment_validate_button);
        button.performClick();
        AlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        assertNotNull(alertDialog);
    }


    @After
    public void tearDown() throws Exception {
        cleanDb();
        Field field = FlowManager.class.getDeclaredField("mDatabaseHolder");
        field.setAccessible(true);
        field.set(null, null);

    }


    public void initDb() {
        specialtyAucune = new Specialty();
        specialtyAucune.setId(0);
        specialtyAucune.setName("Aucune");
        specialtyAucune.save();

        specialtyCardio = new Specialty();
        specialtyCardio.setId(1);
        specialtyCardio.setName("Aliance HealthCare");
        specialtyCardio.save();


        companyAucune = new Company();
        companyAucune.setId(0);
        companyAucune.setName("Aucune");
        companyAucune.save();

        companyDenfert = new Company();
        companyDenfert.setId(1);
        companyDenfert.setName("Maison de Sante Denfert");
        companyDenfert.save();
    }

    public void cleanDb() {
        specialtyAucune.delete();
        specialtyCardio.delete();
        companyAucune.delete();
        companyDenfert.delete();

    }

}
