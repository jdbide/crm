package pds.isintheair.fr.crmtab.registercall.Views.callsnotregistered;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import pds.isintheair.fr.crmtab.R;
import pds.isintheair.fr.crmtab.registercall.Objects.Events.CallEndedEvent;
import pds.isintheair.fr.crmtab.registercall.Objects.Singleton;


public class PendingLogsRecyclerViewAdapter extends RecyclerView.Adapter<PendingLogsRecyclerViewAdapter.ViewHolder> {

    private final List<CallEndedEvent> mValues;
    private final PendingLogsFragment.OnListFragmentInteractionListener mListener;

    public PendingLogsRecyclerViewAdapter(List<CallEndedEvent> items, PendingLogsFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_a_pending_call, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getDate());
        holder.mDate.setText(mValues.get(position).getDate());
        holder.mContact.setText("name cont"/*mValues.get(position).getContactname()*/);
        holder.mClient.setText("name client"/*mValues.get(position).getClientname()*/);

        holder.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("ok", "ok");
            }
        });

        holder.no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Singleton.getInstance().removeItemFromPendingCallList(position);
                }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mDate;
        public final TextView mContact;
        public final TextView mClient;
        public final Button yes;
        public final Button no;
        public CallEndedEvent mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id1);
            mDate = (TextView) view.findViewById(R.id.showdate1);
            mContact = (TextView) view.findViewById(R.id.showcontact1);
            mClient = (TextView) view.findViewById(R.id.showclient1);
            yes = (Button) view.findViewById(R.id.yes);
            no = (Button) view.findViewById(R.id.no);
        }

    }
}
