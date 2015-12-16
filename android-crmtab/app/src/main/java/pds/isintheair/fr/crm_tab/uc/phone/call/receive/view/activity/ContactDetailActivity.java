package pds.isintheair.fr.crm_tab.uc.phone.call.receive.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.entity.Message;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.entity.MessageFactory;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.model.websocket.WebSocketConnectionHandlerSingleton;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.util.JSONHelper;
import pds.isintheair.fr.crm_tab.uc.phone.call.receive.util.enumeration.MessageType;

public class ContactDetailActivity extends Activity {
    @OnClick(R.id.phone_imageview)
    private void onPhoneClick() {
        WebSocketConnectionHandlerSingleton.getInstance()
                                           .sendMessage(JSONHelper.serialize(MessageFactory.buildMessage(
                                                   MessageType.CALL), Message.class));

        startActivity(new Intent(this, CallActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);
        ButterKnife.bind(this);
    }
}
