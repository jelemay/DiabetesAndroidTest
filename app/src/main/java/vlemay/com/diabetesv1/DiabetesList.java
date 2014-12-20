package vlemay.com.diabetesv1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;



public class DiabetesList extends Activity {

    static TextView list_header_Text_Box;
    static TextView size_of_list_Text_Box;
    static ListView glucose_list_ListView;
    static Context myContext;
    static String myOperation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_diabetes_list);

       // listAllIntent.putExtra("listType","user");
        String data = getIntent().getExtras().getString("listType");

        Bundle args = new Bundle();
        args.putString("listType",data);
        GeneralGlucoseListFragment newFragment = new GeneralGlucoseListFragment();
        newFragment.setArguments(args);




        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
           //         .add(R.id.container, new PlaceholderFragment())
                   // .add(R.id.container, new GeneralGlucoseListFragment())
                   .add(R.id.container, newFragment)
                    .commit();
        }
        myContext=this;
        myOperation=data;

       // new LoadListTask().execute(1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.diabetes_list, menu);
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


}
