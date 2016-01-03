package pds.isintheair.fr.crm_tab.client.create.he.test;

import org.junit.Test;

import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.HealthCenter;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.fragment.CreateHCFragment;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.message.MessageRestCustomer;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.message.ResponseRestCustomer;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.rest.RESTCustomerHandlerSingleton;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

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

    @Test
    public void testRestCall() throws Exception {
        HealthCenter healthCenter = new HealthCenter();
        healthCenter.setSiretNumber(1);
        healthCenter.setName("Test");
        MessageRestCustomer mrc = new MessageRestCustomer(1,healthCenter);
        Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance().getCustomerService()
                .createHealthCenter(mrc);
        call.enqueue(new Callback<ResponseRestCustomer>() {
            @Override
            public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
               assertTrue(response.body().getIsInserted());
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });





    }






}
