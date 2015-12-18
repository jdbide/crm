package fr.datour.protoclientrest.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.datour.protoclientrest.R;
import fr.datour.protoclientrest.model.bus.BusHandlerSingleton;
import fr.datour.protoclientrest.model.bus.events.DeleteUserEvent;
import fr.datour.protoclientrest.model.entities.CreateOrUpdateResponse;
import fr.datour.protoclientrest.model.entities.User;
import fr.datour.protoclientrest.model.rest.RESTHandlerSingleton;
import retrofit.Callback;
import retrofit.Response;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ClientViewHolder> {
    RecyclerViewClickListener recyclerViewClickListener;
    private Context    context;
    private List<User> users;

    public UserListAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void add(User user) {
        users.add(user);
        notifyItemInserted(users.size());
    }

    public void remove(Integer position) {
        final User user = users.get(position);

        users.remove(user);

        RESTHandlerSingleton.getInstance().getUserService().deleteUser(user.getId()).enqueue(new Callback<CreateOrUpdateResponse>() {
            @Override
            public void onResponse(Response<CreateOrUpdateResponse> response) {
                if (response.body().getStatus())
                    BusHandlerSingleton.getInstance().getBus().post(new DeleteUserEvent(user));
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

        notifyItemRemoved(position);
    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onBindViewHolder(ClientViewHolder holder, int position) {
        final User user = users.get(position);

        String clientFullName = user.getPrenom() + " " + user.getNom();

        holder.content.setText(clientFullName);
    }

    @Override
    public ClientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClientViewHolder(LayoutInflater.from(context).inflate(R.layout.view_holder_user, parent, false));
    }

    public void setListener(RecyclerViewClickListener listener) {
        recyclerViewClickListener = listener;
    }

    public interface RecyclerViewClickListener {
        void recyclerViewListClicked(View v, int position);
    }

    class ClientViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.content)
        TextView content;

        public ClientViewHolder(final View itemView) {
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