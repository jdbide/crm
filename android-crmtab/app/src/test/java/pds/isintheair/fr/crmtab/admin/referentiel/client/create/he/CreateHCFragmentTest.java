package pds.isintheair.fr.crmtab.admin.referentiel.client.create.he;

import android.app.AlertDialog;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;

import java.lang.reflect.Field;

import fr.pds.isintheair.crmtab.BuildConfig;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.client.activity.CRUDCustomerActivity;
import fr.pds.isintheair.crmtab.client.create.he.entities.HealthCenter;
import fr.pds.isintheair.crmtab.client.create.he.entities.Holding;
import fr.pds.isintheair.crmtab.client.create.he.entities.PurchasingCentral;
import fr.pds.isintheair.crmtab.client.create.he.fragment.CreateHCFragment;
import fr.pds.isintheair.crmtab.client.message.MessageRestCustomer;
import fr.pds.isintheair.crmtab.client.message.ResponseRestCustomer;
import fr.pds.isintheair.crmtab.client.rest.CustomerService;
import retrofit.Callback;
import retrofit.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by tlacouque on 19/12/2015.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, packageName = "pds.isintheair.fr.crmtab")
@RunWith(RobolectricGradleTestRunner.class)
public class CreateHCFragmentTest {
    private CRUDCustomerActivity activity;
    Holding holdingAliance;
    Holding holdingAucune;
    PurchasingCentral purchasingCentralAucune;
    PurchasingCentral purchasingCentralCAHPP;


    @Captor
    private ArgumentCaptor<Callback<ResponseRestCustomer>> cbrrc;

    @Captor
    private ArgumentCaptor<Callback<MessageRestCustomer>> cbmrc;

    @Captor
    private ArgumentCaptor<MessageRestCustomer> messageRestCustomerFragment;

    @Mock
    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        activity = Robolectric.setupActivity(CRUDCustomerActivity.class);

        initDb();

        activity.runOnUiThread(new Runnable() {
            public void run() {
                activity.onPositiveClick(0);
            }
        });
    }


    @Test
    public void testUISpinerInit() throws Exception {
        Spinner spinner = (Spinner) activity.findViewById(R.id.create_he_fragment_etablishment_type);
        assertEquals(spinner.getAdapter().getCount(), 9);
        Spinner spinnerPc = (Spinner) activity.findViewById(R.id.create_he_fragment_purshasing_central);
        assertEquals(spinnerPc.getAdapter().getCount(),2);
        Spinner spinnerHolding = (Spinner) activity.findViewById(R.id.create_he_fragment_holding);
        assertEquals(spinnerHolding.getAdapter().getCount(),2);
    }

    @Test
    public void testGetIntFromRadioGroup() throws Exception {
        RadioGroup radioGroup = (
                RadioGroup) activity.findViewById(R.id.create_he_fragment_difficulty_having_contact);
        RadioButton radioButton0 =
                (RadioButton) activity.findViewById(R.id.create_he_fragment_difficulty_having_contact_zero);
        radioButton0.setChecked(true);
        CreateHCFragment createHCFragment =
                (CreateHCFragment) activity.getFragmentManager().findFragmentById(R.id.create_customer_fragment_container);

        assertEquals(Integer.decode(radioButton0.getText().toString()).intValue()
                , createHCFragment.getIntFromRadiogroup(radioGroup));

    }

    @Test
    public void testInitHCAndInitSpinner() throws Exception {
        final String nameTest = "testName";
        final String siretNumberTest = "26770008600056";
        final String finessNumberTest = "234735732";
        final String streetNumberTest = "1";
        final String streetNameTest = "rue de chalautre";
        final String townNameTest = "Provins";
        final String zipCodeTest = "77160";
        final String bedNumberTest = "200";
        final String webSiteNameTest = "www.test.com";
        EditText name = (EditText) activity.findViewById(R.id.create_he_fragment_name);
        name.setText(nameTest);
        RadioButton isPublicYes = (RadioButton) activity.findViewById(R.id.create_he_fragment_is_public_yes_radio_button);
        isPublicYes.setChecked(true);
        EditText siretNumber = (EditText) activity.findViewById(R.id.create_he_fragment_siret_number);
        EditText finessNumber = (EditText) activity.findViewById(R.id.create_he_fragment_finess_number);
        EditText streetNumber = (EditText) activity.findViewById(R.id.create_he_fragment_street_number);
        EditText streetName = (EditText) activity.findViewById(R.id.create_he_fragment_street_name);
        EditText townName = (EditText) activity.findViewById(R.id.create_he_fragment_town_name);
        EditText zipCode = (EditText) activity.findViewById(R.id.create_he_fragment_zip_code);
        EditText bedNumber = (EditText) activity.findViewById(R.id.create_he_fragment_bed_number);
        EditText webSite = (EditText) activity.findViewById(R.id.create_he_fragment_web_site);
        siretNumber.setText(siretNumberTest);
        finessNumber.setText(finessNumberTest);
        streetNumber.setText(streetNumberTest);
        streetName.setText(streetNameTest);
        townName.setText(townNameTest);
        zipCode.setText(zipCodeTest);
        bedNumber.setText(bedNumberTest);
        webSite.setText(webSiteNameTest);

        CreateHCFragment createHCFragment =
                (CreateHCFragment) activity.getFragmentManager().findFragmentById(R.id.create_customer_fragment_container);

        HealthCenter healthCenter = createHCFragment.initHC();

        assertTrue(healthCenter.isPublic());
        assertEquals(nameTest, healthCenter.getName());
        assertEquals(Long.valueOf(finessNumberTest).longValue(), healthCenter.getFinessNumber());
        assertEquals(Long.valueOf(siretNumberTest).longValue(), healthCenter.getSiretNumber());
        assertEquals(Integer.decode(streetNumberTest).intValue(),healthCenter.getStreetNumber());
        assertEquals(streetNameTest,healthCenter.getStreetName());
        assertEquals(townNameTest,healthCenter.getTown());
        assertEquals(Integer.decode(zipCodeTest).intValue(),healthCenter.getZipCode());
        assertEquals(Integer.decode(bedNumberTest).intValue(),healthCenter.getBedNumber());
        assertEquals(webSiteNameTest,healthCenter.getWebSite());
        assertEquals(0,healthCenter.getHoldingId());
        assertEquals(0,healthCenter.getPurchasingCentralId());

    }


    @Test
    public void testOnValidationSucceedded() throws Exception {
       // Mockito.verify(CustomerService.class).;
        // TODO: 19/01/2016

        int j = (int) new Select().from(HealthCenter.class).count();
        System.out.println("j = "+j);


        final String nameTest = "testName";
        final String siretNumberTest = "26770008600056";
        final String finessNumberTest = "234735732";
        final String streetNumberTest = "1";
        final String streetNameTest = "rue de chalautre";
        final String townNameTest = "Provins";
        final String zipCodeTest = "77160";
        final String bedNumberTest = "200";
        final String webSiteNameTest = "www.test.com";
        EditText name = (EditText) activity.findViewById(R.id.create_he_fragment_name);
        name.setText(nameTest);
        RadioButton isPublicYes = (RadioButton) activity.findViewById(R.id.create_he_fragment_is_public_yes_radio_button);
        isPublicYes.setChecked(true);
        EditText siretNumber = (EditText) activity.findViewById(R.id.create_he_fragment_siret_number);
        EditText finessNumber = (EditText) activity.findViewById(R.id.create_he_fragment_finess_number);
        EditText streetNumber = (EditText) activity.findViewById(R.id.create_he_fragment_street_number);
        EditText streetName = (EditText) activity.findViewById(R.id.create_he_fragment_street_name);
        EditText townName = (EditText) activity.findViewById(R.id.create_he_fragment_town_name);
        EditText zipCode = (EditText) activity.findViewById(R.id.create_he_fragment_zip_code);
        EditText bedNumber = (EditText) activity.findViewById(R.id.create_he_fragment_bed_number);
        EditText webSite = (EditText) activity.findViewById(R.id.create_he_fragment_web_site);
        siretNumber.setText(siretNumberTest);
        finessNumber.setText(finessNumberTest);
        streetNumber.setText(streetNumberTest);
        streetName.setText(streetNameTest);
        townName.setText(townNameTest);
        zipCode.setText(zipCodeTest);
        bedNumber.setText(bedNumberTest);
        webSite.setText(webSiteNameTest);
        CreateHCFragment createHCFragment =
                (CreateHCFragment) activity.getFragmentManager().findFragmentById(R.id.create_customer_fragment_container);
        createHCFragment.onValidationSucceeded();
    /*    CreateHCFragment createHCFragment1 = Mockito.mock(createHCFragment.getClass());
        createHCFragment1.onValidationSucceeded();*/

        MessageRestCustomer messageRestCustomerFragment = createHCFragment.getMessageRestCustomer();
        ArgumentCaptor<MessageRestCustomer> messageRestCustomerArgumentCaptor
                = ArgumentCaptor.forClass(MessageRestCustomer.class);
        HealthCenter healthCenter = createHCFragment.initHC();
        customerService.createHealthCenter(messageRestCustomerFragment);
       // MessageRestCustomer messageRestCustomer = new MessageRestCustomer(0,healthCenter);
        MessageRestCustomer messageRestCustomer = Mockito.mock(MessageRestCustomer.class);
       // CustomerService mockedClients = Mockito.mock(CustomerService.class);
        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setIsInserted(true);
      //  Response<ResponseRestCustomer> response = Mockito.mock(Response.class);
      //  Mockito.when(response.body().getIsInserted()).thenReturn(true);
        createHCFragment.onValidationSucceeded();

     //   Mockito.verify(customerService).createHealthCenter(messageRestCustomerArgumentCaptor.capture());
       // createHCFragment.onValidationSucceeded();
        Response<ResponseRestCustomer> response1 = Response.success(responseRestCustomer);

       // cb.capture();
       // AppMemory.doneSignal.countDown();
        // Retrofit retrofit = new Retrofit();
//        cbrrc.getValue().onResponse(response1, null);

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
        EditText name = (EditText) activity.findViewById(R.id.create_he_fragment_name);
        name.setText(nameTest);
        EditText siretNumber = (EditText) activity.findViewById(R.id.create_he_fragment_siret_number);
        EditText finessNumber = (EditText) activity.findViewById(R.id.create_he_fragment_finess_number);
        EditText streetNumber = (EditText) activity.findViewById(R.id.create_he_fragment_street_number);
        EditText streetName = (EditText) activity.findViewById(R.id.create_he_fragment_street_name);
        EditText townName = (EditText) activity.findViewById(R.id.create_he_fragment_town_name);
        EditText zipCode = (EditText) activity.findViewById(R.id.create_he_fragment_zip_code);
        siretNumber.setText(siretNumberTest);
        finessNumber.setText(finessNumberTest);
        streetNumber.setText(streetNumberTest);
        streetName.setText(streetNameTest);
        townName.setText(townNameTest);
        zipCode.setText(zipCodeTest);
        Button button = (Button) activity.findViewById(R.id.create_he_fragment_validate_button);
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
        holdingAucune = new Holding();
        holdingAucune.setId(0);
        holdingAucune.setName("Aucune");
        holdingAucune.save();

        holdingAliance = new Holding();
        holdingAliance.setId(1);
        holdingAliance.setName("Aliance HealthCare");
        holdingAliance.save();


        purchasingCentralAucune = new PurchasingCentral();
        purchasingCentralAucune.setId(0);
        purchasingCentralAucune.setName("Aucune");
        purchasingCentralAucune.save();

        purchasingCentralCAHPP = new PurchasingCentral();
        purchasingCentralCAHPP.setId(1);
        purchasingCentralCAHPP.setName("CAHPP");
        purchasingCentralCAHPP.save();
    }

    public void cleanDb() {
        holdingAliance.delete();
        holdingAucune.delete();
        purchasingCentralAucune.delete();
        purchasingCentralCAHPP.delete();

    }

}








