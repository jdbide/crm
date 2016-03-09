package fr.pds.isintheair.crmtab.mbalabascarin.uc.normalize;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import android.view.textservice.TextServicesManager;
import android.widget.EditText;

import fr.pds.isintheair.crmtab.R;

public class SpellCheckerFragment extends Fragment implements SpellCheckerSession.SpellCheckerSessionListener {

    private SpellCheckerSession mScs;
    private View view;
    private EditText txtToCheck;

    public SpellCheckerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_spell_checker, container, false);
        txtToCheck = (EditText) view.findViewById(R.id.txtTextToCheck);

        txtToCheck.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mScs.getSuggestions(new TextInfo(txtToCheck.getText().toString()), 3);
            }
        });

        return view;

    }

    public void onResume() {
        super.onResume();
        final TextServicesManager tsm = (TextServicesManager) getActivity().getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE);
        mScs = tsm.newSpellCheckerSession(null, null, this, true);
    }

    public void onPause() {
        super.onPause();
        if (mScs != null) {
            mScs.close();
        }
    }

    @Override
    public void onGetSuggestions(SuggestionsInfo[] suggestionsInfos) {

    }

    @Override
    public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] sentenceSuggestionsInfos) {

    }

}
