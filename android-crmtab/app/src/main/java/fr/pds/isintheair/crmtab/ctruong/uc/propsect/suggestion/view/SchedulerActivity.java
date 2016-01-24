package fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.view;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Bundle;

import java.util.Calendar;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketConnectionHandler;
import de.tavendo.autobahn.WebSocketException;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.model.config.WebsocketConfig;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.notification.receiver.NotificationEventReceiver;

public class SchedulerActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        start();
    }

    private final WebSocketConnection connection = new WebSocketConnection();

    private void start(){
        try {
            connection.connect(WebsocketConfig.WSURI, new WebSocketConnectionHandler(){
                @Override
                public void onOpen() {
                    super.onOpen();
                }

                @Override
                public void onClose(int code, String reason) {
                    super.onClose(code, reason);
                }

                @Override
                public void onTextMessage(String payload) {
                    super.onTextMessage(payload);
                    NotificationEventReceiver.setUpAlarm(getApplicationContext());
                }
            });
        } catch (WebSocketException e) {
            e.printStackTrace();
        }
    }


}
