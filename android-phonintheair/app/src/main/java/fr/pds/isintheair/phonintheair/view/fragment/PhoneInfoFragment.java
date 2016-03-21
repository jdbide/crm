package fr.pds.isintheair.phonintheair.view.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.pds.isintheair.phonintheair.R;

/******************************************
 * Created by        :                    *
 * Creation date     : 03/21/16            *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class PhoneInfoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phone_info, container, false);



        return view;
    }
}
