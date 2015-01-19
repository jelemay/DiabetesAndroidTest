package vlemay.com.diabetesv1;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import vlemay.com.diabetesv1.client.PatientSvcApi;
import vlemay.com.diabetesv1.model.GlucoseEventData;
import vlemay.com.diabetesv1.model.GlucoseEventRequest;
import vlemay.com.diabetesv1.service.PatientSvc;


public class DiabetesAddEvent extends Activity {

    GlucoseEventRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes_add_event);
        final EditText concentrationValue = (EditText) findViewById(R.id.concentrationValue);
        final EditText deviceIdValue = (EditText) findViewById(R.id.deviceIdValue);

        final RadioButton isBeforeMealValue = (RadioButton) findViewById(R.id.isBeforeMealValue);
        final RadioButton isAfterMealValue = (RadioButton) findViewById(R.id.isAfterMealValue);

        Button glucoseEventAddButton = (Button) findViewById(R.id.glucoseEventAddButton);
        final Context myContext = this.getApplicationContext();

        glucoseEventAddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.i("jl", "clicked the glucose event add  button");

                if (concentrationValue.getText().toString().equals("") ||
                        deviceIdValue.getText().toString().equals("") )
                      {

                    Toast.makeText(myContext, "All inputs are required",
                            Toast.LENGTH_LONG).show();

                }
                else
                {
                    double concentration = Double.valueOf(concentrationValue.getText().toString());

                    Long deviceId = Long.valueOf(deviceIdValue.getText().toString());
                    Boolean isBeforeMeal = isBeforeMealValue.isChecked();
                    Boolean isAfterMeal = isAfterMealValue.isChecked();

                    request = new GlucoseEventRequest();
                    request.setConcentration(concentration);
                    request.setIsBeforeMeal(isBeforeMeal);
                    request.setIsAfterMeal(isAfterMeal);
                    request.setDeviceId(deviceId);


                    Log.i("jl", "request instance data");
                    Log.i("jl", Double.toString(request.getConcentration()));
                    Log.i("jl", Long.toString(request.getDeviceId()));
                    Log.i("jl", Boolean.toString(isBeforeMeal));
                    Log.i("jl", Boolean.toString(isAfterMeal));


                    new AddEventTask().execute(1);
                }

            }


        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.diabetes_add_event, menu);
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


    public class AddEventTask extends AsyncTask<Integer, Integer, GlucoseEventData> {


        @Override
        protected void onPreExecute() {
            Log.i("jl", "Started the add event  pre-execute task");

        }

        @Override
        protected GlucoseEventData doInBackground(Integer... resId) {
            Log.i("jl", "Executing the async task");


            PatientSvcApi patientSVC = PatientSvc.getOrShowLogin(DiabetesAddEvent.this.getApplicationContext());


            Log.i("jl", "Sending the Put");
            GlucoseEventData ev = patientSVC.addGlucoseEvent(request);
            Log.i("jl", "Received the results from the put");

            return ev;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // mProgressBar.setProgress(values[0]);
            // Log.i("jl", "Doing something with progress");

        }

        @Override
        protected void onPostExecute(GlucoseEventData result) {
            Context myContext = DiabetesAddEvent.this.getApplicationContext();
            Toast.makeText(myContext, "The event was added",
                    Toast.LENGTH_LONG).show();
        }
    }





}
