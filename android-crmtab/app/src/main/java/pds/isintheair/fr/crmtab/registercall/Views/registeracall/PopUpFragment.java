package pds.isintheair.fr.crmtab.registercall.Views.registeracall;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import pds.isintheair.fr.crmtab.MainActivity;
import pds.isintheair.fr.crmtab.registercall.Objects.Singleton;

/**
 * Created by j-d on 18/12/2015.
 */

public class PopUpFragment extends DialogFragment {

    int mNum;

    /**
     * Create a new instance of MyDialogFragment, with  the callEnded event params
     * as arguments.
     */
    public static PopUpFragment newInstance(String idcontact) {
        PopUpFragment f = new PopUpFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("idcontact", idcontact);


        f.setArguments(args);

        return f;
    }




   /* @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(400, 400);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }*/
//3 buttons yes,no or later : problem no box title



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       super.onCreateDialog(savedInstanceState);
        //two buttons yes and no
        return 	new AlertDialog.Builder(getActivity()).setTitle("Enregistrer dernier appel?").setMessage("Enregistrer dernier appel ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //hide popup , addlog fragment  is below
                        Singleton.getInstance().setPopUpDisplayed(false);
                    }
                }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Singleton.getInstance().setPopUpDisplayed(false);
                        //remove addlogactivity (popup fragment & addlogfragment
                        //getActivity().onBackPressed();
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }
                }).show();

    }

}
