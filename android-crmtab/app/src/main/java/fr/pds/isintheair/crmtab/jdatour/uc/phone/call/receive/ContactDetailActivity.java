package fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.jdatour.uc.phone.call.receive.controller.CallController;

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
    }
}