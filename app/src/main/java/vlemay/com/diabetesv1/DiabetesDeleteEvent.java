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

import vlemay.com.diabetesv1.client.PatientSvcApi;
import vlemay.com.diabetesv1.service.PatientSvc;


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

            PatientSvcApi patientSVC = PatientSvc.getOrShowLogin(myContext);
;
            boolean isEventDeleted = patientSVC.deleteGlucoseEvent(glucoseIdL);

            return isEventDeleted;
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
