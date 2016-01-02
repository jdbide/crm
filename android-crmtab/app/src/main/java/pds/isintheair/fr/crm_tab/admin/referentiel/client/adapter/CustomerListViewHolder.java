package pds.isintheair.fr.crm_tab.admin.referentiel.client.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.Customer;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.he.entities.HealthCenter;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.create.indep.entities.Independant;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.display.hc.fragment.DetailHCFragment;
import pds.isintheair.fr.crm_tab.admin.referentiel.client.display.indep.fragment.DetailIndepFragment;

/**
 * Created by tlacouque on 01/01/2016.
 * Represent a line in ListCustomerAdapter
 */
public class CustomerListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ListCustomerAdapter listCustomerAdapter;
    Context context;

    @Bind(R.id.fragment_customer_adapter_name_textview)
    TextView name;

    @Bind(R.id.fragment_customer_adapter_image_imageview)
    ImageView image;


    /**
     * Constructor, take listCustomerAdapter to call it when the is a click on the list
     * @param itemView
     * @param listCustomerAdapter
     */
    public CustomerListViewHolder(View itemView,ListCustomerAdapter listCustomerAdapter) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.listCustomerAdapter = listCustomerAdapter;
        this.context = listCustomerAdapter.getContext();
        itemView.setClickable(true);
        itemView.setOnClickListener(this);
    }

    /**
     * Called when a new viewHolder is created. Is used to set value in attributes
     * @param customer
     */
    public void bind(Customer customer){
        if(customer instanceof HealthCenter) image.setImageResource(R.drawable.list_customer_hc);
        else image.setImageResource(R.drawable.list_customer_indep);

        name.setText(customer.getName());
    }

    /**
     * Called when the user click on view holder.
     * @param v
     */
    @Override
    public void onClick(View v) {
        Customer customer = listCustomerAdapter.getCustomers().get(this.getLayoutPosition());
        if(customer instanceof HealthCenter) startDetailHCFragment((HealthCenter) customer);
        else startDetailIndepFragment((Independant) customer);


    }

    /**
     * Called when a click was made on an health center
     * Begin detailHCFragment
     * @param healthCenter
     */
    void startDetailHCFragment(HealthCenter healthCenter) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailHCFragment.KEY_HC_ARGS, healthCenter);
        DetailHCFragment detailHCFragment = new DetailHCFragment();
        detailHCFragment.setArguments(bundle);
        ((AppCompatActivity)context).getSupportActionBar()
                .setTitle(R.string.display_hc_fragment_title_action_bar);
        ((AppCompatActivity)context).getFragmentManager().beginTransaction().addToBackStack("list")
                .replace(R.id.create_customer_fragment_container, detailHCFragment).commit();
    }

    /**
     * Called when a click was made on an independant
     * Begin DetailIndepFragment
     * @param independant
     */
    private void startDetailIndepFragment(Independant independant) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailIndepFragment.KEY_INDEP_ARGS, independant);
        DetailIndepFragment detailIndepFragment = new DetailIndepFragment();
        detailIndepFragment.setArguments(bundle);
        ((AppCompatActivity)context).getSupportActionBar()
                .setTitle(R.string.display_independant_fragment_title_action_bar);
        ((AppCompatActivity)context).getFragmentManager().beginTransaction().addToBackStack("list")
                .replace(R.id.create_customer_fragment_container, detailIndepFragment).commit();
    }


}
