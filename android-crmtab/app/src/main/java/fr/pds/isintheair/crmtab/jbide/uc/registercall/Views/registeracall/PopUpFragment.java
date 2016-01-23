package fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.registeracall;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.view.activity.MainActivity;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Constants;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Events.CallEndedEvent;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Events.DisplayAddLogFragment;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.enums.CallType;

/**
 * Created by j-d on 18/12/2015.
 */

public class PopUpFragment extends DialogFragment {

    private static DisplayAddLogFragment callevent;
    int mNum;
    //private CallEndedEvent callevent;

    /**
     * Create a new instance of MyDialogFragment, with  the callEnded event params
     * as arguments.
     */
    public static PopUpFragment newInstance(DisplayAddLogFragment event) {
        PopUpFragment f = new PopUpFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        callevent = event;


        f.setArguments(args);

        return f;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       super.onCreateDialog(savedInstanceState);
        //two buttons yes and no
        return 	new AlertDialog.Builder(getActivity()).setTitle("Enregistrer dernier appel?").setMessage("Enregistrer dernier appel ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //hide popup , addlog fragment  is below
                        Constants.getInstance().setPopUpDisplayed(false);
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);

                        AddLogFragment fragment = AddLogFragment.newInstance(callevent.getCallEndedEvent().getIdcontact()
                                , callevent.getCallEndedEvent().getDate()
                                , callevent.getCallEndedEvent().getDuration()
                                , callevent.getCallEndedEvent().getCalltype() == CallType.INCOMING ? "Entrant" : "Sortant"
                                , callevent.getCallEndedEvent().getDisplaypopUp());

                        ft.replace(R.id.container, fragment, "FRAGMENT_AJOUT").addToBackStack(null).commit();

                    }
                }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Constants.getInstance().setPopUpDisplayed(false);
                        //remove addlogactivity (popup fragment & addlogfragment
                        //getActivity().onBackPressed();
                        //startActivity(new Intent(getActivity(), MainActivity.class));
                        //getActivity().getFragmentManager().popBackStack();
                    }
                }).show();

    }

}
