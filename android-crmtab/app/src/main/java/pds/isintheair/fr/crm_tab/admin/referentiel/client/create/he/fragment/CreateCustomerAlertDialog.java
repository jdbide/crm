package pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.enums.CustomerType;
import pds.isintheair.fr.crm_tab.R;

/**
 * Created by user on 12/12/2015.
 */
public class CreateCustomerAlertDialog extends DialogFragment {

    static String[] customerTypeList = new String[] {
            CustomerType.HEALTH_ETABLISHMENT.toString(),
            CustomerType.INDEPENDENT.toString()
    };


    AlertPositiveListener alertPositiveListener;


    public interface AlertPositiveListener {
        public void onPositiveClick(int position);
    }


    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
        try{
            alertPositiveListener = (AlertPositiveListener) activity;
        }catch(ClassCastException e){
            // The hosting activity does not implemented the interface AlertPositiveListener
            throw new ClassCastException(activity.toString() + " must implement AlertPositiveListener");
        }
    }

    /** This is the OK button listener for the alert dialog,
     *  which in turn invokes the method onPositiveClick(position)
     *  of the hosting activity which is supposed to implement it
     */
    DialogInterface.OnClickListener positiveListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            AlertDialog alert = (AlertDialog)dialog;
            int position = alert.getListView().getCheckedItemPosition();
            alertPositiveListener.onPositiveClick(position);
        }
    };


    /** This is a callback method which will be executed
     *  on creating this fragment
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        /** Getting the arguments passed to this fragment */
        Bundle bundle = getArguments();
        int position = bundle.getInt("position");

        /** Creating a builder for the alert dialog window */
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());

        /** Setting a title for the window */
        b.setTitle(R.string.customer_list_dialog_title);



        /** Setting items to the alert dialog */
        b.setSingleChoiceItems(CreateCustomerAlertDialog.customerTypeList, position, null);

        /** Setting a positive button and its listener */
        b.setPositiveButton(R.string.customer_list_dialog_validate,positiveListener);

        /** Setting a positive button and its listener */
        b.setNegativeButton(R.string.customer_list_dialog_cancel, null);

        /** Creating the alert dialog window using the builder class */
        AlertDialog d = b.create();

        /** Return the alert dialog window */
        return d;
    }

}
