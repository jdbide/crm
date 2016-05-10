package fr.pds.isintheair.crmtab.jbide.uc.registercall;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Service;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.ContactsContract;

public class ContactService extends Service {

    private int mContactCount;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContactCount = getContactCount();
        this.getContentResolver().registerContentObserver(
                ContactsContract.Contacts.CONTENT_URI, true, mObserver);
    }

    private int getContactCount() {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(
                    ContactsContract.Contacts.CONTENT_URI, null, null, null,
                    null);
            if (cursor != null) {
                return cursor.getCount();
            } else {
                return 0;
            }
        } catch (Exception ignore) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return 0;
    }

    private ContentObserver mObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            final int currentCount = getContactCount();
            if (currentCount < mContactCount) {
                // DELETE HAPPEN.
            } else if (currentCount == mContactCount) {
                // UPDATE HAPPEN
                String id, name, phone, hasPhone;
                int idx;

                Cursor cur = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);


                if(cur.moveToFirst()) {

                    while (cur.isAfterLast() == false) {
                        String nam  = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        String phoneNumber = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                        cur.moveToNext();
                    }
                }

                cur.close();


            } else {
                // INSERT HAPPEN.
            }
            mContactCount = currentCount;
        }
    };


    public void test(){
        Account[] accountList = AccountManager.get(this).getAccounts();

        String accountSelection = "";
        for(int i = 0 ; i < accountList.length ; i++) {
            if(accountSelection.length() != 0)
                accountSelection = accountSelection + " AND ";
            accountSelection = accountSelection + ContactsContract.Groups.ACCOUNT_TYPE + " != '" +  accountList[i].type + "'";
            accountSelection.contains("");
        }
    }






    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(mObserver);
    }

}