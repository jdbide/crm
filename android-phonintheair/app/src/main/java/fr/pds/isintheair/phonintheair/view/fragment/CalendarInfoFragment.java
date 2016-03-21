package fr.pds.isintheair.phonintheair.view.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.phonintheair.R;
import fr.pds.isintheair.phonintheair.helper.SharedPreferencesHelper;
import fr.pds.isintheair.phonintheair.model.entity.Agenda;
import fr.pds.isintheair.phonintheair.model.provider.CalendarProvider;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 03/21/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class CalendarInfoFragment extends Fragment {
    @Bind(R.id.connection_state_switch)
    ImageView connectionStateSwitch;

    @Bind(R.id.calendar_presentation_text_View)
    TextView calendarPresentationTextView;

    @Bind(R.id.calendar_choice_button)
    Button calendarChoiceButton;

    @OnClick(R.id.calendar_choice_button)
    public void onCalendarChoiceButton() {
        CalendarProvider    calendarProvider = new CalendarProvider(getContext());
        final List<Agenda>  agendas          = calendarProvider.getAgendas("");
        AlertDialog.Builder builder          = new AlertDialog.Builder(getActivity());

        List<String> agendaNames = new ArrayList<>();

        for (Agenda agenda : agendas) {
            agendaNames.add(agenda.getName());
        }

        builder.setTitle("Veuillez choisir un agenda")
               .setItems(agendaNames.toArray(new CharSequence[agendaNames.size()]), new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       Agenda agendaChosen             = agendas.get(which);
                       String calendarPresentationText = "Agenda choisi : " + agendaChosen.getName();

                       SharedPreferencesHelper.writeLong("agendaId", agendaChosen.getId());

                       calendarPresentationTextView.setText(calendarPresentationText);
                       calendarChoiceButton.setVisibility(View.GONE);
                   }
               }).create().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_info, container, false);

        ButterKnife.bind(this, view);

        Long agendaId = SharedPreferencesHelper.readLong("agendaId", 42);

        if (agendaId != 42) {
            CalendarProvider calendarProvider = new CalendarProvider(getContext());
            Agenda agenda = calendarProvider.getAgendaById(agendaId);
            String calendarPresentationText = "Agenda choisi : " + agenda.getName();

            calendarPresentationTextView.setText(calendarPresentationText);
            calendarChoiceButton.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
