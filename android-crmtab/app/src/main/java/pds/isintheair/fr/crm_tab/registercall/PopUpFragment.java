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

public class PopUpFragment extends DialogFragment {

    int mNum;

    /**
     * Create a new instance of MyDialogFragment, with  the callEnded event params
     * as arguments.
     */
    static PopUpFragment newInstance(int num,String idcontact) {
        PopUpFragment f = new PopUpFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("idcontact", idcontact);


        f.setArguments(args);

        return f;
    }

    /*public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt("num");

        // Pick a style based on the num.
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        switch ((mNum-1)%6) {
            case 1: style = DialogFragment.STYLE_NO_TITLE; break;
            case 2: style = DialogFragment.STYLE_NO_FRAME; break;
            case 3: style = DialogFragment.STYLE_NO_INPUT; break;
            case 4: style = DialogFragment.STYLE_NORMAL; break;
            case 5: style = DialogFragment.STYLE_NORMAL; break;
            case 6: style = DialogFragment.STYLE_NO_TITLE; break;
            case 7: style = DialogFragment.STYLE_NO_FRAME; break;
            case 8: style = DialogFragment.STYLE_NORMAL; break;
        }
        switch ((mNum-1)%6) {
            case 4: theme = android.R.style.Theme_Holo; break;
            case 5: theme = android.R.style.Theme_Holo_Light_Dialog; break;
            case 6: theme = android.R.style.Theme_Holo_Light; break;
            case 7: theme = android.R.style.Theme_Holo_Light_Panel; break;
            case 8: theme = android.R.style.Theme_Holo_Light; break;
        }
        setStyle(style, theme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialogbox_layout, container, false);
        View tv = v.findViewById(R.id.popupTitle);
        ((TextView)tv).setText("Dialog #" + mNum + ": using style ");
             //   + getNameForNum(mNum));

        //get ended call args
        final String idcontact = getArguments().getString("idcontact");

        // Watch for button clicks.
        Button button = (Button)v.findViewById(R.id.oui);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddLogActivity.class);
                intent.putExtra("idcontact", idcontact);
                startActivity(intent);

            }

        });


        return v;
    }*/

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

                        startActivity(new Intent(getActivity(),AddLogActivity.class));
                        //getActivity().finish();
                        Singleton.getInstance().setPopUpDisplayed(false);
                    }
                }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Singleton.getInstance().setPopUpDisplayed(false);
                    }
                }).show();

    }

}
