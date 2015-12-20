package pds.isintheair.fr.crm_tab.crv.view;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.crv.mock.MockClient;
import pds.isintheair.fr.crm_tab.crv.model.Client;

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
        options.add("CRV");
        options.add("Agenda");
        options.add("Tâches");
        options.add("Information");

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View promptView = layoutInflater.inflate(R.layout.options_layout, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptView);

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

                if(itemValue.equalsIgnoreCase("crv")){
                    Intent intent = new Intent(getActivity(), CreateCrvActivity.class);
                    intent.putExtra("ClientObject", client);
                    startActivity(intent);
                }
            }

        });

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)

                .setNegativeButton("Fermer",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }

}
