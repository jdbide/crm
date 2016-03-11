package fr.pds.isintheair.crmtab.mbalabascarin.uc.normalize;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import android.view.textservice.TextServicesManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import fr.pds.isintheair.crmtab.R;

public class NormalizeDemoActivity extends Activity implements SpellCheckerSession.SpellCheckerSessionListener {

    private SpellCheckerSession mScs;
    private View view;
    private EditText txtToCheck;
    private TextView sugg1,sugg2,sugg3,sugg4,sugg5;
    private Button btnCheck;
    ListView lstAdress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_check);
        txtToCheck = (EditText) findViewById(R.id.txtTextToCheck);
        sugg1 = (TextView) findViewById(R.id.txtSugg1);
        sugg2 = (TextView) findViewById(R.id.txtSugg2);
        sugg3 = (TextView) findViewById(R.id.txtSugg3);
        sugg4 = (TextView) findViewById(R.id.txtSugg4);
        sugg5 = (TextView) findViewById(R.id.txtSugg5);
        final TextServicesManager tsm = (TextServicesManager) getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE);
        mScs = tsm.newSpellCheckerSession(null, null, this, true);
        txtToCheck.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String testString = txtToCheck.getText().toString();
                String[] parts = testString.split(" ");
                String lastWord = parts[parts.length - 1];

                try {
                    mScs.getSuggestions(new TextInfo(lastWord), 5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onResume() {
        super.onResume();
        final TextServicesManager tsm = (TextServicesManager) getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE);
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
        try {
            if(!sb.toString().contains("(0)")) {
                sugg1.setText(sb.toString().split(",")[0]);
                sugg2.setText(sb.toString().split(",")[1]);
                sugg3.setText(sb.toString().split(",")[2]);
                sugg4.setText(sb.toString().split(",")[3]);
                sugg5.setText(sb.toString().split(",")[4]);
            }else{
                sugg1.setText("");
                sugg2.setText("");
                sugg3.setText("");
                sugg4.setText("");
                sugg5.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Toast.makeText(NormalizeDemoActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] sentenceSuggestionsInfos) {

    }
    public void replaceText(View view){

        String lastWord = txtToCheck.getText().toString().replaceAll("^.*?(\\w+)\\W*$", "$1");
        String phrase = txtToCheck.getText().toString();

        switch (view.getId()) {
            case R.id.txtSugg1:

                String result = phrase.replaceAll(lastWord, sugg1.getText().toString());
                txtToCheck.setText(result);
                break;
            case R.id.txtSugg2:

                String result1 = phrase.replaceAll(lastWord, sugg2.getText().toString());
                txtToCheck.setText(result1);
                break;
            case R.id.txtSugg3:

                String result2 = phrase.replaceAll(lastWord, sugg3.getText().toString());
                txtToCheck.setText(result2);
                break;
            case R.id.txtSugg4:

                String result3 = phrase.replaceAll(lastWord, sugg4.getText().toString());
                txtToCheck.setText(result3);
                break;
            case R.id.txtSugg5:

                String result4 =phrase.replaceAll(lastWord, sugg5.getText().toString());
                txtToCheck.setText(result4);
                break;
        }

        Toast.makeText(NormalizeDemoActivity.this, "clicked", Toast.LENGTH_SHORT).show();

    }

    public String normalizeNumber(String number){

        return PhoneNumberUtils.normalizeNumber(number);
    }

    public String formatTelNumber(String number){
        return PhoneNumberUtils.formatNumber(normalizeNumber(number), Locale.getDefault().getCountry());
    }


    public void checkPhoneNumber(View view){
        EditText tel = (EditText) findViewById(R.id.txtTelCheck);
        TextView lblMessage = (TextView) findViewById(R.id.lblMessage);

        String formattedNumber = formatTelNumber(tel.getText().toString());
        tel.setText(formattedNumber);
        if(checkIfRightNumber(formattedNumber)){
            lblMessage.setText("Error in phone number");
        }else{
            lblMessage.setText("");
        }
    }
    public boolean checkIfRightNumber(String phoneNumber){
        return PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber);
    }

    public void checkAdresse(View view){
        EditText txtAddress = (EditText) findViewById(R.id.txtAdresseCheck);
        TextView error = (TextView) findViewById(R.id.lblMessage1);
        if(!checkWithGeocode(txtAddress.getText().toString())){
            error.setText("Adresse non valide !");
        }
    }




}
