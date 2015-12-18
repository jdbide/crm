package fr.pds.isintheair.phonintheair.controller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

import fr.pds.isintheair.phonintheair.PhointheairApp;

public class CallController {
    public static void call(String phoneNumber) {
        Intent  callIntent         = new Intent(Intent.ACTION_CALL);
        Context applicationContext = PhointheairApp.context;

        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            applicationContext.startActivity(callIntent);
        }
    }
}
