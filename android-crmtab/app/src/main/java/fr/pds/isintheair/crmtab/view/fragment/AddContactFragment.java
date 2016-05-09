package fr.pds.isintheair.crmtab.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.pds.isintheair.crmtab.R;



/**
 * Created by jbide on 09/05/2016.
 */
public class AddContactFragment extends Fragment {
    @Bind(R.id.edittextnumber)
    EditText number;
    @Bind(R.id.edittextname)
    EditText name;
    @Bind(R.id.registercontact)
    Button register;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addcontact, container, false);
        ButterKnife.bind(this, view);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
