package fr.pds.isintheair.crmtab.jbide.uc.registercall.Views.registeracall;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;


import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.view.activity.MainActivity;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Constants;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Events.DisplayAddLogFragment;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.Events.DisplayPopUpFragment;
import fr.pds.isintheair.crmtab.jbide.uc.registercall.enums.CallType;

/**
 * Created by j-d on 18/12/2015.
 */

public class PopUpFragment extends DialogFragment {

    private static DisplayPopUpFragment callevent;
    int mNum;
    //private CallEndedEvent callevent;

    /**
     * Create a new instance of MyDialogFragment, with  the callEnded event params
     * as arguments.
     */
    public static PopUpFragment newInstance(DisplayPopUpFragment event) {
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
                        ((MainActivity)getActivity()).showaddlogfragment(new DisplayAddLogFragment(callevent.getCallEndedEvent()));


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
