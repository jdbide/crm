package pds.isintheair.fr.crm_tab.registercall.Views.displaycalls;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import pds.isintheair.fr.crm_tab.R;
import pds.isintheair.fr.crm_tab.registercall.Rest.Model.Cra;
import pds.isintheair.fr.crm_tab.registercall.Views.displaycalls.DisplayCallLogFragment.OnListFragmentInteractionListener;
import pds.isintheair.fr.crm_tab.registercall.dummy.DummyContent.DummyItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CallLogRecyclerViewAdapter extends RecyclerView.Adapter<CallLogRecyclerViewAdapter.ViewHolder> {

    private final List<Cra> liste;
    private final OnListFragmentInteractionListener mListener;

    public CallLogRecyclerViewAdapter(List<Cra> items, OnListFragmentInteractionListener listener) {
        liste = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_calllog_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = liste.get(position);
        holder.mIdView.setText(liste.get(position).getDate());
        holder.mDate.setText(liste.get(position).getDate());
        holder.mContact.setText(liste.get(position).getContactname());
        holder.mClient.setText(liste.get(position).getClientname());
        holder.mSubject.setText(liste.get(position).getSubject());
        //holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
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
        return liste.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       // @Bind(R.id.showsubject) TextView mSubjet;
        public final View mView;
        public final TextView mIdView;
        public final TextView mDate;
        public final TextView mContact;
        public final TextView mClient;
        public final TextView mSubject;
        public Cra mItem;

        public ViewHolder(View view) {
            super(view);
            //ButterKnife.bind(this, view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mDate = (TextView) view.findViewById(R.id.showdate);
            mSubject = (TextView) view.findViewById(R.id.showsubject);
            mClient = (TextView) view.findViewById(R.id.showclient);
            mContact = (TextView) view.findViewById(R.id.showcontact);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mSubject.getText() + "'";
        }
    }
}
