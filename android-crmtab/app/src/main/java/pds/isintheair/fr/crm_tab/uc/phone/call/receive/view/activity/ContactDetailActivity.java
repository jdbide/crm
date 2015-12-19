package pds.isintheair.fr.crm_tab.uc.phone.call.receive.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.controller.CallController;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.controller.service.CallService;

public class ContactDetailActivity extends Activity {
    @OnClick(R.id.phone_imageview)
    public void onPhoneClick() {
        CallController.call("0610772364");

        startActivity(new Intent(this, CallActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);
        ButterKnife.bind(this);
        startService(new Intent(this, CallService.class));
    }
}
