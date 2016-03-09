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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.pds.isintheair.crmtab.R;

public class SpellCheckerFragment extends Fragment implements SpellCheckerSession.SpellCheckerSessionListener {

    private SpellCheckerSession mScs;
    private View view;
    private EditText txtToCheck;
    private Button btnCheck;

    public SpellCheckerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_spell_checker, container, false);
        txtToCheck = (EditText) view.findViewById(R.id.txtTextToCheck);
        btnCheck = (Button) view.findViewById(R.id.btnCheck);
        final TextServicesManager tsm = (TextServicesManager) getActivity().getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE);
        mScs = tsm.newSpellCheckerSession(null, null, this, true);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mScs.getSuggestions(new TextInfo(txtToCheck.getText().toString()), 3);
            }
        });

        txtToCheck.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

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

        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < suggestionsInfos.length; ++i) {
            // Returned suggestions are contained in SuggestionsInfo
            final int len = suggestionsInfos[i].getSuggestionsCount();
            sb.append('\n');

            for (int j = 0; j < len; ++j) {
                sb.append("," + suggestionsInfos[i].getSuggestionAt(j));
            }

            sb.append(" (" + len + ")");
        }
        Toast.makeText(getActivity(), sb.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] sentenceSuggestionsInfos) {

    }

}
