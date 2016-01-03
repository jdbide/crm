package pds.isintheair.fr.crmtab.registercall.Views.callsnotregistered;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import pds.isintheair.fr.crmtab.R;
import pds.isintheair.fr.crmtab.registercall.Objects.Events.CallEndedEvent;


public class APendingCallRegisteredRecyclerViewAdapter extends RecyclerView.Adapter<APendingCallRegisteredRecyclerViewAdapter.ViewHolder> {

    private final List<CallEndedEvent> mValues;
    private final APendingCallFragment.OnListFragmentInteractionListener mListener;

    public APendingCallRegisteredRecyclerViewAdapter(List<CallEndedEvent> items, APendingCallFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_a_pending_call, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getDate());
        holder.mDate.setText(mValues.get(position).getDate());
        holder.mContact.setText("name cont"/*mValues.get(position).getContactname()*/);
        holder.mClient.setText("name client"/*mValues.get(position).getClientname()*/);

        holder.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

        holder.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
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

        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText() + "'";
        }
    }
}
