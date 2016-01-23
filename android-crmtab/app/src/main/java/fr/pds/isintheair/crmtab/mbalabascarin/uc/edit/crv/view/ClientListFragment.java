package fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.view;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.controller.CrvController;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.mock.MockClient;
import fr.pds.isintheair.crmtab.mbalabascarin.uc.edit.crv.model.Client;


public class ClientListFragment extends ListFragment {


    List<String> clients = new ArrayList<String>();
    List<Client> mockedClients = new ArrayList<Client>();
    MockClient mockClient;
   ListView listView;
    Client client;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //get 5 mocked clients
        mockClient = new MockClient();
        mockedClients = mockClient.getClients();
        for(Client client : mockedClients){
            clients.add(client.getClientId() +" "+client.getClientSurname() +" "+client.getClientName() +"-"+client.getClientAddress());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, clients);

        setListAdapter(adapter);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
         client = mockedClients.get(position);

        launchListDialog();
    }

    public void launchListDialog(){
        //create option list
        final List<String> options = new ArrayList<String>();
        options.add("Contact");
        options.add("Visite");
        options.add("Agenda");
        options.add("Tâches");
        options.add("Information");

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View promptView = layoutInflater.inflate(R.layout.options_layout, null);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptView);

        // setup a dialog window
        alertDialogBuilder.setCancelable(true)

                .setNegativeButton("Fermer",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        final AlertDialog alert = alertDialogBuilder.create();

        // Get ListView object from xml
        listView = (ListView) promptView.findViewById(R.id.lstOptions);

        // Define a new Adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, options);

        // Assign adapter to ListView
        listView.setAdapter(adapter1);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                if(itemValue.equalsIgnoreCase("visite")){



                    new CrvController().getAllReportForClient(Integer.toString(client.getClientId()), client, getActivity());
                    alert.cancel();

                }
            }

        });


        alert.show();

    }

}
