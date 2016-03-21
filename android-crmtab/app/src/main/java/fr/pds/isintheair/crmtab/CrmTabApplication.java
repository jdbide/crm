package fr.pds.isintheair.crmtab;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import fr.pds.isintheair.crmtab.helper.JSONHelper;
import fr.pds.isintheair.crmtab.model.dao.ContactDAO;
import fr.pds.isintheair.crmtab.model.mock.Contact;

public class CrmTabApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
        context = getApplicationContext();

        //TODO Remove it when contacts are properly handled
        generateMockedContactsIfNeeded();
    }

    private void generateMockedContactsIfNeeded() {
        List<Contact> databaseContacts = ContactDAO.getAll();

        if (databaseContacts.size() == 0) {

            StringBuilder stringBuilder = new StringBuilder();
            InputStream inputStream = null;

            try {
                inputStream = context.getAssets().open("contact-mock.json");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String input;

                while ((input = bufferedReader.readLine()) != null) {
                    stringBuilder.append(input);
                }

                bufferedReader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            String contactJson = stringBuilder.toString();
            Contact[] contactsJSon = (Contact[]) JSONHelper.deserialize(contactJson, Contact[].class);
            List<Contact> contacts = Arrays.asList(contactsJSon);

            for (int i = 0; i < contacts.size(); ++i) {
                Contact contact = contacts.get(i);
                contact.save();
            }
        }
    }
}