package fr.pds.isintheair.phonintheair.view.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.phonintheair.R;
import fr.pds.isintheair.phonintheair.controller.bus.handler.BusHandlerSingleton;
import fr.pds.isintheair.phonintheair.helper.SharedPreferencesHelper;
import fr.pds.isintheair.phonintheair.model.entity.Agenda;
import fr.pds.isintheair.phonintheair.model.provider.CalendarProvider;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 03/21/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class DashboardActivity extends Activity {
    @Bind(R.id.call_connection_state)
    ImageView callConnectionState;

    @Bind(R.id.call_autorisation_state)
    ImageView callAutorisationState;

    @Bind(R.id.calendar_connection_state)
    ImageView calendarConnectionState;

    @Bind(R.id.calendar_autorisation_state)
    ImageView calendarAutorisationState;

    @Bind(R.id.calendar_chosen_textview)
    TextView calendarChosenTextview;

    @OnClick(R.id.calendar_choice_button)
    public void onCalendarChoiceButton() {
        CalendarProvider    calendarProvider = new CalendarProvider(this);
        final List<Agenda>  agendas          = calendarProvider.getAgendas("");
        AlertDialog.Builder builder          = new AlertDialog.Builder(this);

        List<String> agendaNames = new ArrayList<>();

        for (Agenda agenda : agendas) {
            agendaNames.add(agenda.getName());
        }

        builder.setTitle("Veuillez choisir un agenda")
               .setItems(agendaNames.toArray(new CharSequence[agendaNames.size()]), new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       Agenda agendaChosen = agendas.get(which);

                       SharedPreferencesHelper.writeLong("agendaId", agendaChosen.getId());

                       calendarChosenTextview.setText(agendaChosen.getName());
                   }
               }).create().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ButterKnife.bind(this);
        BusHandlerSingleton.getInstance().getBus().register(this);

        List<String> permissions = new ArrayList<>();

        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.CALL_PHONE);
        }

        if (checkSelfPermission(Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.READ_CALENDAR);
        }

        if (checkSelfPermission(Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_CALENDAR);
        }

        if (permissions.size() > 0) {
            requestPermissions(permissions.toArray(new String[permissions.size()]), 0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Long agendaId = SharedPreferencesHelper.readLong("agendaId", 42);

        if (checkSelfPermission(Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            calendarAutorisationState.setImageResource(R.drawable.red_circle);
        }

        if (agendaId != 42) {
            CalendarProvider calendarProvider = new CalendarProvider(this);
            Agenda agenda = calendarProvider.getAgendaById(agendaId);

            calendarChosenTextview.setText(agenda.getName());
        }

        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            callAutorisationState.setImageResource(R.drawable.red_circle);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callAutorisationState.setImageResource(R.drawable.green_circle);
            }

            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                calendarAutorisationState.setImageResource(R.drawable.green_circle);
            }
        }
    }

    /* @Subscribe
    public void onCalendarWebSocketConnectionLost(CalendarWebSocketConnectionLostEvent event) {
        calendarConnectionState.setImageResource(R.drawable.red_circle);
    }

    @Subscribe
    public void onCalendarWebSocketConnectionRetrevied(CalendarWebSocketConnectionRetrievedEvent event) {
        calendarConnectionState.setImageResource(R.drawable.green_circle);
    }

    @Subscribe
    public void onCallWebSocketConnectionLost(CallWebSocketConnectionLostEvent event) {
        callConnectionState.setImageResource(R.drawable.red_circle);
    }

    @Subscribe
    public void onCallWebSocketConnectionRetrevied(CallWebSocketConnectionRetrievedEvent event) {
        callConnectionState.setImageResource(R.drawable.green_circle);
    } */
}
