package com.bench.tlacouque.benchgreendao;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private PersonDao personDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "person-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        personDao = daoSession.getPersonDao();

        //Add by TLA
        List note = personDao.queryBuilder()
                .list();
        if(note.isEmpty()) {
            initNoteBase();
        }

        Long start = System.nanoTime();
        List notes = personDao.queryBuilder()
                .where(PersonDao.Properties.Comment.eq("comment 5"))
                .list();
        Log.d("Temps requête", "" + String.valueOf(System.nanoTime() - start));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initNoteBase() {
        int j = 0;
        for(int i=0; i<999 ;i++) {
            j++;
            Person person = new Person((long) i,"name"+i,"comment "+j);
            personDao.insert(person);
            if(j == 10) j =-1;
        }
    }
}
