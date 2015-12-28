package pds.isintheair.fr.crm_tab.admin.referentiel.client.rest;

import com.raizlabs.android.dbflow.sql.language.Select;

import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.Holding;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.PurchasingCentral;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities.Company;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities.Specialty;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.message.ResponseRestCustomer;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by tlacouque on 28/12/2015.
 */
public final class InitValue {

    public static void doInit() {
        initHolding();
        initPurchasingCentral();
        initCompanies();
        initSpecialties();
    }

    private static void initHolding() {

        if(new Select().count().from(Holding.class).count() == 0) {

            Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance().getCustomerService().getHoldings();

            call.enqueue(new Callback<ResponseRestCustomer>() {
                @Override
                public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
                    for (Holding holding : response.body().getHoldings()) {
                        holding.save();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

    private static void initPurchasingCentral() {

        if(new Select().count().from(PurchasingCentral.class).count() == 0) {

            Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance().getCustomerService().getPurchasingCentral();

            call.enqueue(new Callback<ResponseRestCustomer>() {
                @Override
                public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
                    for (PurchasingCentral purchasingCentral : response.body().getPurchasingCentrals()) {
                        purchasingCentral.save();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

    private static void initCompanies() {

        if(new Select().count().from(Company.class).count() == 0) {

            Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance().getCustomerService().getCompanies();

            call.enqueue(new Callback<ResponseRestCustomer>() {
                @Override
                public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
                    for (Company company : response.body().getCompanies()) {
                        company.save();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

    private static void initSpecialties() {

        if(new Select().count().from(Specialty.class).count() == 0) {

            Call<ResponseRestCustomer> call = RESTCustomerHandlerSingleton.getInstance()
                    .getCustomerService().getSpecialties();

            call.enqueue(new Callback<ResponseRestCustomer>() {
                @Override
                public void onResponse(Response<ResponseRestCustomer> response, Retrofit retrofit) {
                    for (Specialty specialty : response.body().getSpecialties()) {
                        specialty.save();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }


}
