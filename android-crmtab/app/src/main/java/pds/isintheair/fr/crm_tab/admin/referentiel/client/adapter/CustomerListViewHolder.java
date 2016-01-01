package pds.isintheair.fr.crm_tab.admin.referentiel.client.adapter;

import android.media.Image;
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

/**
 * Created by tlacouque on 01/01/2016.
 */
public class CustomerListViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.fragment_customer_adapter_name_textview)
    TextView name;

    @Bind(R.id.fragment_customer_adapter_image_imageview)
    ImageView image;

    public CustomerListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(Customer customer){
        if(customer instanceof HealthCenter) image.setImageResource(R.drawable.list_customer_hc);
        else image.setImageResource(R.drawable.list_customer_indep);

        name.setText(customer.getName());
    }



}
