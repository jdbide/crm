package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.model.rest.CheckInternetConnexion;
import fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment.DetailFragmentNetworkInterface;

/**
 * Created by tlacouque on 02/03/2016.
 * Used to detect if the network connectivity change
 */
public class NetworkReceiver extends BroadcastReceiver {

    DetailFragmentNetworkInterface fragmentNetworkInterface;
    public boolean networkAvailable;

    public NetworkReceiver(DetailFragmentNetworkInterface fragment) {
        fragmentNetworkInterface = fragment;
    }

    public NetworkReceiver() {
    }

    /**
     * Called when internet connexion changed, it will change the fragment.
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if(CheckInternetConnexion.isNetworkAvailable(context)) {
            fragmentNetworkInterface.initOnlineMap();
            networkAvailable = true;
        } else {
            fragmentNetworkInterface.initOfflineMap(true);
            networkAvailable = false;
        }
    }
}
