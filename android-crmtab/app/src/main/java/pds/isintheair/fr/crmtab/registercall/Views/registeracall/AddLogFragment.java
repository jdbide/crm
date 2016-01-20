package pds.isintheair.fr.crmtab.registercall.Views.registeracall;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pds.isintheair.fr.crmtab.R;
import pds.isintheair.fr.crmtab.registercall.Objects.Singleton;
import pds.isintheair.fr.crmtab.registercall.Rest.ControllerCra;
import pds.isintheair.fr.crmtab.registercall.Rest.Model.Cra;
import pds.isintheair.fr.crmtab.registercall.Views.displaycalls.DisplayCallLogFragment;

public class AddLogFragment extends Fragment {

    @Bind(R.id.formtitle) TextView formtitle;
    @Bind(R.id.edittextcontatctname) EditText contactname;
    @Bind(R.id.edittextclientname) EditText clientname;
    @Bind(R.id.edittextcontatctnumber) EditText contactnumber;
    @Bind(R.id.edittextduration) EditText duration;
    @Bind(R.id.edittextcomments) EditText comments;
    @Bind(R.id.edittextsubject) EditText subject;
    @Bind(R.id.edittextdate) EditText date;
    @Bind(R.id.edittextcalltype) EditText calltype;
    @Bind(R.id.buttonregistercra)  Button validation;
    @Bind(R.id.vocalcomment)  Button mic;


    public static AddLogFragment newInstance(String idcontact,String date,String duration,String calltype) {
        AddLogFragment f = new AddLogFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("idcontact", idcontact);
        args.putString("duration", duration);
        args.putString("date", date);
        args.putString("calltype", calltype);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide  keyboard
        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_informations_fragment, container, false);
        ButterKnife.bind(this, view);

        /*User user = new User();
        user.setTel("0123456789");
        user.setMdp("password");
         ControllerCra.hasAccount(user, getActivity());*/
        //Log.v("account?",b.toString());



        formtitle.setText("Ajout d'un compte-rendu");
        contactnumber.setText(getArguments().getString("idcontact"));
        date.setText(getArguments().getString("date"));
        duration.setText(getArguments().getString("duration"));
        calltype.setText(getArguments().getString("calltype"));
        //should be taken from a request
        contactname.setText("nom contactttt");
        clientname.setText("nom client");
        mic.setBackground(getResources().getDrawable(R.drawable.mic1));
        validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cra newCra = new Cra();
                newCra.setCalltype(calltype.getText().toString());
                newCra.setClientname(clientname.getText().toString());
                newCra.setComments(comments.getText().toString());
                newCra.setContactname(contactname.getText().toString());
                newCra.setDate(date.getText().toString());
                newCra.setDuration(Long.parseLong(String.valueOf(duration.getText())));
                newCra.setIdcontact(Long.parseLong(contactnumber.getText().toString()));
                newCra.setSubject(subject.getText().toString());
                newCra.setIduser((long) Singleton.getInstance().getCurrentUser().getTel());

                ControllerCra.registerCra(newCra,getActivity());
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.addlogmenu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);

        switch(item.getItemId())
        {
            case R.id.item1:

                /*bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(), "1034", "11111111"));
                bus.post(new CallEndedEvent(CallType.OUTGOING, Calendar.getInstance().getTime().toLocaleString(), "502", "33333333"));
                bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(), "1038", "5555555"));
                bus.post(new CallEndedEvent(CallType.INCOMING, Calendar.getInstance().getTime().toLocaleString(), "1034", "7777777777"));*/

                return true;

            case R.id.item2:
                DisplayCallLogFragment fragment = DisplayCallLogFragment.newInstance(1) ;
                ft.replace(R.id.container, fragment, "FRAGMENT_AJOUT").addToBackStack(null).commit();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
