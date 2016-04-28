package fr.pds.isintheair.crmtab.controller.message;

import fr.pds.isintheair.crmtab.model.entity.ResponseRestPhoningCampaign;
import fr.pds.isintheair.crmtab.view.fragment.CreatePhoningCampaignFragment;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by tlacouque on 26/04/2016.
 */
public class CallBackGetContactsForPhoningCampaign implements Callback<ResponseRestPhoningCampaign> {

    CreatePhoningCampaignFragment fragment;

    public CallBackGetContactsForPhoningCampaign() {
    }

    public CallBackGetContactsForPhoningCampaign(CreatePhoningCampaignFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onResponse(Response<ResponseRestPhoningCampaign> response, Retrofit retrofit) {
        if (response.errorBody() == null) {
            if (response.body() != null) {
                fragment.restartCampaign(response.body().getContacts());

            }

        } else {
            onFailure(null);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        fragment.callRestFailed();
    }
}
