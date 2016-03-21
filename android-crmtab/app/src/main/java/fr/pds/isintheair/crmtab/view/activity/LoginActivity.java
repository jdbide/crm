package fr.pds.isintheair.crmtab.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.controller.service.CalendarService;
import fr.pds.isintheair.crmtab.controller.service.CallService;
import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.notification.service.NotificationIntentService;
import fr.pds.isintheair.crmtab.helper.CredentialHelper;
import fr.pds.isintheair.crmtab.model.dao.UserDAO;
import fr.pds.isintheair.crmtab.model.entity.User;
import fr.pds.isintheair.crmtab.model.rest.RetrofitHandlerSingleton;
import fr.pds.isintheair.crmtab.model.rest.service.LoginService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by jbide on 22/01/2016.
 */

/**
 * Fixed by jbide on 26/01/2016 : 17:00
 */
public class LoginActivity extends Activity implements Callback<User> {
    @Bind(R.id.login_edittext)
    EditText loginEditText;

    @Bind(R.id.password_edittext)
    EditText passwordEditText;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private User currentUser;

    @OnClick(R.id.connection_button)
    public void onConnectionClick() {
        progressBar.setVisibility(View.VISIBLE);

        String login    = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String basic    = CredentialHelper.getBase64Credentials(login, password);

        currentUser.setEmail(login);
        currentUser.setPassword(basic);



        LoginService loginService = RetrofitHandlerSingleton.getInstance().getLoginService();
        Call<User> call         = loginService.login(currentUser);

        call.enqueue(LoginActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginEditText.setText("test@crm.fr");
        passwordEditText.setText("password");
        progressBar.setVisibility(View.GONE);

        currentUser = UserDAO.getCurrentUser();

        if (currentUser != null) {
            startActivity(new Intent(this, MainActivity.class));
        }

        else {
            currentUser = new User();
            onConnectionClick();
        }
    }

    @Override
    public void onResponse(Response<User> response, Retrofit retrofit) {
        if (response.isSuccess()) {
            currentUser = response.body();

            //TODO FIx the real problem
            if (currentUser == null) {
                currentUser = new User();

                currentUser.setEmail("test@crm.fr");
            }

            currentUser.save();

            startService(new Intent(LoginActivity.this, CallService.class));
            startService(new Intent(LoginActivity.this, CalendarService.class));
            startService(new Intent(LoginActivity.this, NotificationIntentService.class));
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    @Override
    public void onFailure(Throwable t) {
        currentUser.delete();
    }
}