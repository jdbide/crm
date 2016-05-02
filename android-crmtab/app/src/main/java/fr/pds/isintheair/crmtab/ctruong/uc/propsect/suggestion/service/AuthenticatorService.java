package fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import fr.pds.isintheair.crmtab.ctruong.uc.propsect.suggestion.account.Authenticator;

public class AuthenticatorService extends Service {

    private Authenticator authenticator;
    public AuthenticatorService() {
        authenticator = new Authenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return authenticator.getIBinder();
    }
}
