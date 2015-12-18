package pds.isintheair.fr.crm_tab.isin.truong.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.isin.truong.model.entity.Prospect;

/**
 * Created by Truong on 12/14/2015.
 */
public class ProspectAdapter extends RecyclerView.Adapter<ProspectAdapter.ProspectViewHolder> {
    private RecyclerViewClickListener recyclerViewClickListener;
    private Context context;
    private List<Prospect> prospects;

    /**
     * Constructor
     *
     * @param context
     * @param prospects
     */
    public ProspectAdapter(Context context, List<Prospect> prospects) {
        this.context = context;
        this.prospects = prospects;
    }

    /**
     * Create new view
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ProspectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProspectViewHolder(LayoutInflater.from(context).inflate(R.layout.view_holder_prospect, parent, false));
    }

    /**
     * bind method
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ProspectViewHolder holder, int position) {
        final Prospect prospect = prospects.get(position);
        String prospectName = prospect.getName() + " - " + prospect.getCity();
        holder.content.setText(prospectName);
    }

    /**
     * Setter
     *
     * @param listener
     */
    public void setListener(RecyclerViewClickListener listener) {
        recyclerViewClickListener = listener;
    }

    /**
     * Interface to take care listener
     */
    public interface RecyclerViewClickListener {
        void recyclerViewListClicked(View v, int position);
    }

    /**
     * Getter
     * @return
     */
    public List<Prospect> getProspects() {
        return prospects;

    }

    /**
     * Setter
     * @param prospects
     */
    public void setProspects(List<Prospect> prospects) {
        this.prospects = prospects;
    }

    /**
     * Counting position
     * @return
     */
    @Override
    public int getItemCount() {
        return prospects.size();
    }

    /**
     * Intern class
     */
    class ProspectViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.content)
        TextView content;

        public ProspectViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClickListener.recyclerViewListClicked(itemView, getLayoutPosition());
                }
            });

        }
    }
}
