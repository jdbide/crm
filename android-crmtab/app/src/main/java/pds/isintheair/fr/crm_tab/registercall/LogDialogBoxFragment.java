package pds.isintheair.fr.crm_tab.registercall;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by j-d on 18/12/2015.
 */

public class LogDialogBoxFragment extends DialogFragment {
//3 buttons yes,no or later : problem no box title

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Set title for this dialog
        getDialog().setTitle(R.string.stringregistercalldialogbox);
        LinearLayout layout=
                new LinearLayout(getActivity());
        //Selection buttons
        Button Oui = new Button(getActivity());
        Button Non = new Button(getActivity());
        Button PlusTard = new Button(getActivity());
        Oui.setText("Oui");
        Non.setText("Non");
        PlusTard.setText("Plus Tard");
        //Add buttons to layout
        layout.addView(Oui);
        layout.addView(Non);
        layout.addView(PlusTard);
        return layout;
    }*/

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       super.onCreateDialog(savedInstanceState);
        //3 buttons yes,no or later no box title

        /*final Dialog dialog = new Dialog(getActivity());
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

                        startActivity(new Intent(getActivity(),AddLogActivity.class));
                        //getActivity().finish();
                    }
                }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

}
