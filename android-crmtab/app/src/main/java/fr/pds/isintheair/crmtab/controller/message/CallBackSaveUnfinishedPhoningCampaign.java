package fr.pds.isintheair.crmtab.controller.message;

import fr.pds.isintheair.crmtab.model.entity.ResponseRestPhoningCampaign;
import fr.pds.isintheair.crmtab.view.fragment.CreatePhoningCampaignFragment;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by tlacouque on 12/04/2016.
 */
public class CallBackSaveUnfinishedPhoningCampaign implements Callback<ResponseRestPhoningCampaign> {

    CreatePhoningCampaignFragment fragment;


    public CallBackSaveUnfinishedPhoningCampaign(CreatePhoningCampaignFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onResponse(Response<ResponseRestPhoningCampaign> response, Retrofit retrofit) {
        boolean bool = false;
        if (response.errorBody() == null) {
            if(response.body().isSaved()) {
                bool = true;
            }
        }
        fragment.stopCampaign(bool);
    }

    @Override
    public void onFailure(Throwable t) {
        fragment.stopCampaign(false);
    }
}
