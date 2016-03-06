package fr.pds.isintheair.crmtab.tlacouque.uc.admin.ref.customer.view.fragment;

/**
 * Created by tlacouque on 05/03/2016.
 * Interface used to be called when the network connectivity change and has to be
 */
public interface DetailFragmentNetworkInterface {
    void initClientLocation(boolean offline);
    void  initOnlineMap();
}
