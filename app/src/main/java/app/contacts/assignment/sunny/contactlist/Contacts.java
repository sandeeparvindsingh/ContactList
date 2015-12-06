package app.contacts.assignment.sunny.contactlist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.MyAdapter;
import pojo.Persons;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Contacts extends Activity implements TextWatcher {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contacts);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewxml);
        search = (EditText)findViewById(R.id.search_contact);

        search.addTextChangedListener(this);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ArrayList<Persons> arraylist =new ArrayList<Persons>();


        arraylist = readContacts();

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(arraylist, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

        }


    public ArrayList<Persons> readContacts()
    {
        ArrayList<Persons> arraylist =new ArrayList<Persons>();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, ContactsContract.Contacts.SORT_KEY_PRIMARY + " ASC");
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {

                Persons person=new Persons();
                String number = "Not exist";
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                String URI=null;
                try {

                    URI = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));


                }catch (Exception e)
                {
                    URI ="android.resource://"+getApplicationContext().getPackageName()+"/drawable/icon";
                }



                person.setContact_id(id);
                person.setName(name);
                person.setProfilePic(URI);

                Toast.makeText(getApplicationContext(), "ID: " + id + "  " + "NAME: " + name + "   PHOTO:", Toast.LENGTH_SHORT).show();
                arraylist.add(person);
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    //Query phone here.  Covered next
                }
            }
        }

return arraylist;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }


/**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
}
