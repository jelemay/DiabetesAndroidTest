package vlemay.com.diabetesv1;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;


public class DiabetesDeleteEvent extends Activity {
    String myOperation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes_delete_event);

        myOperation = getIntent().getExtras().getString("listType");

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.diabetes_delete_event, menu);

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
    public  static class PlaceholderFragment extends Fragment {
        Context myContext;
        Long glucoseIdL;

        String myOperation="delete";

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_diabetes_delete_event, container, false);
            final Button deleteEventButton = (Button) rootView.findViewById(R.id.deleteEventSubmitButton);
            final TextView eventId =(TextView)rootView.findViewById(R.id.deleteEventInputTextField);
            Log.i("jl", "got the buttons, about to set the delete submit button");

            //listUserButton.setOnClickListener(new View.OnClickListener() {

            Log.i("jl", "the class of the button");
            Log.i("jl", deleteEventButton.toString());


            deleteEventButton.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Log.i("jl", "clicked the delete event button");
                    String eId = eventId.getText().toString();

                    if(eventId.getText().toString().equals("")){
                        myContext = PlaceholderFragment.this.getActivity().getApplicationContext();
                        Toast.makeText(myContext, "An event Id is required",
                        Toast.LENGTH_LONG).show();

                    }
                    else {

                        glucoseIdL=Long.valueOf(eId);
                        new DeleteListTask().execute(1);
                    }
                }



            });


            return rootView;
        }



    public class DeleteListTask extends AsyncTask<Integer, Integer, Boolean> {


        @Override
        protected void onPreExecute() {
            Log.i("jl", "Started the delete pre-execute task");

        }

        @Override
        protected Boolean doInBackground(Integer... resId) {
            Log.i("jl", "Executing the async task");
            Log.i("jl", "Operation ID as seen in Async Task");
            Log.i("jl", myOperation);

            String TEST_URL = "http://vlemay.com:8080/diabetes-0.2.0";

            GsonBuilder builder = new GsonBuilder();

            builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                @Override
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
                    String date = json.getAsJsonPrimitive().getAsString();
                    long dateL = Long.valueOf(date);

                    Log.i("jl", "Date long to string");
                    Log.i("jl", String.valueOf(dateL));

                    Date dateReturn = new Date(dateL);

                    return dateReturn;
                }
            });
            //  .registerTypeAdapter(Date.class, new DateTypeAdapter())

            Gson gson = builder.create();

            DiabetesSvcApi diabetesSVC = new RestAdapter.Builder()
                    .setEndpoint(TEST_URL)
                    .setConverter(new GsonConverter(gson))
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build()
                    .create(DiabetesSvcApi.class);

            //public boolean deleteGlucoseEvent(@Path("glucoseID") long glucoseId);
            List<GlucoseEvent> gList = diabetesSVC.getGlucoseList();

            Iterator<GlucoseEvent> gIt = gList.iterator();
            GlucoseEvent temp;
            boolean exists = false;
            boolean isDeleted;
            while(gIt.hasNext()){
                temp=gIt.next();
                if(temp.getId() == glucoseIdL){
                    exists=true;
                }
            }
            if(exists){
                 isDeleted = diabetesSVC.deleteGlucoseEvent(glucoseIdL);
            }
            else{
                isDeleted =false;
            }




            return isDeleted;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // mProgressBar.setProgress(values[0]);
            // Log.i("jl", "Doing something with progress");

        }

        @Override
        protected void onPostExecute(Boolean result) {

            if(result) {

                Log.i("jl", "Delete was successful");
                Context myContext = PlaceholderFragment.this.getActivity().getApplicationContext();
                Toast.makeText(myContext, "The Event was deleted",
                        Toast.LENGTH_LONG).show();
            }
            else{
                Log.i("jl", "Delete failed");
                Context myContext = PlaceholderFragment.this.getActivity().getApplicationContext();
                Toast.makeText(myContext, "The Event was not Deleted",
                        Toast.LENGTH_LONG).show();
            }

        }
    }
    }
}
