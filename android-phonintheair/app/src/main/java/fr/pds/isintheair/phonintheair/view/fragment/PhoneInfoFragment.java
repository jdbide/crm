package fr.pds.isintheair.phonintheair.view.fragment;


import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.pds.isintheair.phonintheair.R;

/******************************************
 * Created by        :                    *
 * Creation date     : 03/21/16            *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class PhoneInfoFragment extends Fragment {
    @Bind (R.id.autorisation_state)
    ImageView autorisationState;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phone_info, container, false);

        ButterKnife.bind(this, view);

        if (getContext().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            autorisationState.setImageResource(R.drawable.red_circle);
        }

        return view;
    }
}
