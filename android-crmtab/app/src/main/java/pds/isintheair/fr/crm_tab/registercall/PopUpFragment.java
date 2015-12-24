package pds.isintheair.fr.crm_tab.registercall;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by j-d on 18/12/2015.
 */

public class PopUpFragment extends DialogFragment {

    int mNum;

    /**
     * Create a new instance of MyDialogFragment, with  the callEnded event params
     * as arguments.
     */
    static PopUpFragment newInstance(String idcontact) {
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
        //3 buttons yes,no or later no box title

       /* final Dialog dialog = new Dialog(getActivity());
        //dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.dialogbox_layout);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        //mButton = (Button) dialog.findViewById(R.id.button1);
        //mEditText = (EditText) dialog.findViewById(R.id.editText1);
        //mEditText.setText(text);
        return dialog;*/

        //two buttons yes and no
        return 	new AlertDialog.Builder(getActivity()).setTitle("Enregistrer dernier appel?").setMessage("Enregistrer dernier appel ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing, just hide popup , addlog form is already below
                        Singleton.getInstance().setPopUpDisplayed(false);
                    }
                }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Singleton.getInstance().setPopUpDisplayed(false);
                        //remove addlogactivity (popup fragment & addlogfragment
                        getActivity().finish();
                    }
                }).show();

    }

}
