package pds.isintheair.fr.crm_tab.admin.referentiel.client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.Customer;

/**
 * Created by tlacouque on 29/12/2015.
 */
public class ListCustomerAdapter extends ArrayAdapter<Customer> {

    public ListCustomerAdapter(Context context, int resource, List<Customer> customers) {
        super(context, 0, customers);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_customer_adapter, parent, false);
        }

        Customer customer = getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.fragment_customer_adapter_name_textview);
        textView.setText(customer.getName());
        return view;
    }

}
