package fr.pds.isintheair.crmtab.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import fr.pds.isintheair.crmtab.R;

/**
 * Created by tlacouque on 26/03/2016.
 */
public class createPhoningCampaignFragment extends Fragment {

    public createPhoningCampaignFragment() {
        // Required empty public constructor
    }

    /**
     * Can be called when a new DetailHCFragment is needed
     *
     * @return DetailHCFragment
     */
    public static createPhoningCampaignFragment newInstance() {
        createPhoningCampaignFragment fragment = new createPhoningCampaignFragment();
        Bundle args     = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_phoning_campaign, container, false);
        ButterKnife.bind(this, v);
        return v;
    }
}
