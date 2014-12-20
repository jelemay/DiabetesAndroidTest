package vlemay.com.diabetesv1;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Diabetes extends Activity {
    static Intent listAllIntent;
    static Intent listDeviceIntent;
    static Intent listUserIntent;
    static Intent deleteByIdIntent;
    static Intent addEventIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        listAllIntent = new Intent(this,DiabetesList.class);
        listDeviceIntent = new Intent(this,DiabetesDeviceList.class);
        listUserIntent = new Intent(this,DiabetesUserList.class);
        deleteByIdIntent = new Intent(this,DiabetesDeleteEvent.class);
        addEventIntent = new Intent(this,DiabetesAddEvent.class);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.diabetes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_diabetes, container, false);
            // about button and its listener
            Button listAllButton = (Button) rootView
                    .findViewById(R.id.list_button);
            listAllButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Log.i("jl", "clicked the list button");
                    listAllIntent.putExtra("listType","all");
                    startActivity(listAllIntent);

                }
            });

            Button listUserButton = (Button) rootView
                    .findViewById(R.id.list_user_events_button);
            listUserButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Log.i("jl", "clicked the User list button");
                    listUserIntent.putExtra("listType","user");
                    startActivity(listUserIntent);

                }
            });

            Button listDeviceButton = (Button) rootView
                    .findViewById(R.id.list_device_events_button);
            listDeviceButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Log.i("jl", "clicked the Device list button");
                    listDeviceIntent.putExtra("listType","user");
                    startActivity(listDeviceIntent);

                }
            });

            Button deleteByIdButton = (Button) rootView
                    .findViewById(R.id.deleteButton);
            deleteByIdButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Log.i("jl", "clicked the event delete button");
                    deleteByIdIntent.putExtra("listType","delete");
                    startActivity(deleteByIdIntent);

                }
            });

            Button addEventButton = (Button) rootView
                    .findViewById(R.id.addEventButton);
            addEventButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Log.i("jl", "clicked the event add button");
                    addEventIntent.putExtra("listType","add");
                    startActivity(addEventIntent);

                }
            });


            return rootView;
        }
    }
}
