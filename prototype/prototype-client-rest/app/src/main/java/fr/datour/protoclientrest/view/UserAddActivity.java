package fr.datour.protoclientrest.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.datour.protoclientrest.R;
import fr.datour.protoclientrest.model.bus.BusHandlerSingleton;
import fr.datour.protoclientrest.model.bus.events.CreateUserEvent;
import fr.datour.protoclientrest.model.entities.CreateOrUpdateResponse;
import fr.datour.protoclientrest.model.entities.User;
import fr.datour.protoclientrest.model.rest.RESTHandlerSingleton;
import fr.datour.protoclientrest.model.rest.UserService;
import retrofit.Callback;
import retrofit.Response;

public class UserAddActivity extends AppCompatActivity {
    @Bind(R.id.first_name)
    EditText firstName;

    @Bind(R.id.last_name)
    EditText lastName;

    @Bind(R.id.validate)
    Button validate;
    private Boolean isUpdate;
    private User    currentUser;

    @OnClick(R.id.validate)
    public void createOrUpdateUser() {
        currentUser.setNom(firstName.getText().toString());
        currentUser.setPrenom(lastName.getText().toString());

        UserService userService = RESTHandlerSingleton.getInstance().getUserService();

        BusHandlerSingleton.getInstance().getBus().post(new CreateUserEvent(currentUser));

        Callback<CreateOrUpdateResponse> callback = new Callback<CreateOrUpdateResponse>() {
            @Override
            public void onResponse(Response<CreateOrUpdateResponse> response) {
                if (response.body() != null && response.body().getStatus())
                    finish();
            }

            @Override
            public void onFailure(Throwable t) {
            }
        };

        if (isUpdate)
            userService.updateUser(currentUser.getId(), currentUser.getNom(), currentUser.getPrenom()).enqueue(callback);

        else
            userService.createUser(currentUser.getNom(), currentUser.getPrenom()).enqueue(callback);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Parcelable extra = getIntent().getParcelableExtra("User");

        if (extra != null) {
            currentUser = (User) extra;
            isUpdate = true;

            firstName.setText(currentUser.getPrenom());
            lastName.setText(currentUser.getNom());
            validate.setText(getString(R.string.content_user_add_validate_button_update));
        } else {
            currentUser = new User();
            isUpdate = false;

            validate.setText(getString(R.string.content_user_add_validate_button_create));
        }
    }

    private void stopActivity() {
        finishActivity(RESULT_OK);
    }
}
