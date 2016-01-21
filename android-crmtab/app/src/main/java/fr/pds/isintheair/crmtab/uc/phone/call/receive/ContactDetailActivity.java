package fr.pds.isintheair.crmtab.uc.phone.call.receive;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.crmtab.uc.phone.call.receive.controller.CallController;
import pds.isintheair.fr.crmtab.R;

public class ContactDetailActivity extends Activity {
    @OnClick(R.id.phone_imageview)
    public void onPhoneClick() {
        CallController.call("0610772364");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);
        ButterKnife.bind(this);
    }
}