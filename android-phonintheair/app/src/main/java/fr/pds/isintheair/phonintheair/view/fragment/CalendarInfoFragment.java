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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.phonintheair.R;
import fr.pds.isintheair.phonintheair.helper.GoogleAccountHelper;

/******************************************
 * Created by        :                    *
 * Creation date     : 03/21/16            *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class CalendarInfoFragment extends Fragment {
    @Bind(R.id.account_presentation_textView)
    TextView accountPresentationTextView;

    @Bind(R.id.connection_state_switch)
    ImageView connectionStateSwitch;

    @Bind(R.id.account_choice_button)
    Button accountChoiceButton;

    @Bind(R.id.calendar_presentation_text_View)
    TextView calendarPresentationTextView;

    @Bind(R.id.calendar_choice_button)
    Button calendarChoiceButton;

    @OnClick(R.id.account_choice_button)
    public void onAccountChoiceClick() {
        final List<String>  accountNames = GoogleAccountHelper.getAccountNames(getContext());
        AlertDialog.Builder builder      = new AlertDialog.Builder(getActivity());

        builder.setTitle("Veuillez choisir un compte")
               .setItems(accountNames.toArray(new CharSequence[accountNames.size()]), new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       String accountChosen           = accountNames.get(which);
                       String accountPresentationText = "Compte choisi : " + accountChosen;

                       accountPresentationTextView.setText(accountPresentationText);
                       accountChoiceButton.setVisibility(View.GONE);
                   }
               }).create().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_info, container, false);

        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
