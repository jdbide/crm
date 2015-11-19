package fr.datour.protoclientrest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.datour.protoclientrest.R;
import fr.datour.protoclientrest.controller.UserListAdapter;
import fr.datour.protoclientrest.model.bus.BusHandlerSingleton;
import fr.datour.protoclientrest.model.entities.User;
import fr.datour.protoclientrest.model.rest.RESTHandlerSingleton;
import retrofit.Callback;
import retrofit.Response;

public class UserListActivity extends AppCompatActivity implements UserListAdapter.RecyclerViewClickListener {
    private final Integer USER_ADD_REQUEST = 1;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.users_recycler_view)
    RecyclerView usersRecyclerView;
    @Bind(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;
    private List<User>      users;
    private UserListAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(this);
        BusHandlerSingleton.getInstance().getBus().register(this);

        refreshUsers();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserListActivity.this, UserAddActivity.class);
                startActivity(intent);
            }
        });

        users = new Select().from(User.class).queryList();

        userListAdapter = new UserListAdapter(this, users);

        userListAdapter.setListener(this);
        usersRecyclerView.setAdapter(userListAdapter);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                userListAdapter.remove(viewHolder.getLayoutPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(usersRecyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshUsers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void refreshUsers() {
        RESTHandlerSingleton.getInstance().getUserService().getUsers().enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Response<ArrayList<User>> response) {
                users = response.body();

                BusHandlerSingleton.getInstance().getBus().post(users);
                userListAdapter.setUsers(users);
                userListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(UserListActivity.this, "Failed to refresh users : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        Intent intent = new Intent(UserListActivity.this, UserAddActivity.class);

        intent.putExtra("User", users.get(position));

        startActivity(intent);
    }
}
