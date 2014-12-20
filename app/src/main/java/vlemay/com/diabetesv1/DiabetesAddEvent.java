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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;


public class DiabetesAddEvent extends Activity {

    GlucoseEventRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes_add_event);
        final EditText concentrationValue = (EditText) findViewById(R.id.concentrationValue);
        final EditText deviceIdValue = (EditText) findViewById(R.id.deviceIdValue);
        final EditText userIdValue = (EditText) findViewById(R.id.userIdValue);
        final RadioButton isBeforeMealValue = (RadioButton) findViewById(R.id.isBeforeMealValue);
        final RadioButton isAfterMealValue = (RadioButton) findViewById(R.id.isAfterMealValue);

        Button glucoseEventAddButton = (Button) findViewById(R.id.glucoseEventAddButton);
        final Context myContext = this.getApplicationContext();

        glucoseEventAddButton.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                Log.i("jl", "clicked the glucose event add  button");

                if (concentrationValue.getText().toString().equals("") ||
                        deviceIdValue.getText().toString().equals("") ||
                        userIdValue.getText().toString().equals("")) {

                    Toast.makeText(myContext, "All inputs are required",
                            Toast.LENGTH_LONG).show();

                }
                else
                {
                    double concentration = Double.valueOf(concentrationValue.getText().toString());
                    Long userId = Long.valueOf(userIdValue.getText().toString());
                    Long deviceId = Long.valueOf(deviceIdValue.getText().toString());
                    Boolean isBeforeMeal = isBeforeMealValue.isChecked();
                    Boolean isAfterMeal = isAfterMealValue.isChecked();

                    request = new GlucoseEventRequest();
                    request.setConcentration(concentration);
                    request.setIsBeforeMeal(isBeforeMeal);
                    request.setIsAfterMeal(isAfterMeal);
                    request.setDeviceId(deviceId);
                    request.setUserId(userId);

                    Log.i("jl", "request instance data");
                    Log.i("jl", Double.toString(request.getConcentration()));
                    Log.i("jl", Long.toString(request.getUserId()));
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


    public class AddEventTask extends AsyncTask<Integer, Integer, GlucoseEvent> {


        @Override
        protected void onPreExecute() {
            Log.i("jl", "Started the add event  pre-execute task");

        }

        @Override
        protected GlucoseEvent doInBackground(Integer... resId) {
            Log.i("jl", "Executing the async task");


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

            Log.i("jl", "Sending the Put");
            GlucoseEvent ev = diabetesSVC.addGlucoseEvent(request);
            Log.i("jl", "Received the results from the put");

            return ev;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // mProgressBar.setProgress(values[0]);
            // Log.i("jl", "Doing something with progress");

        }

        @Override
        protected void onPostExecute(GlucoseEvent result) {
            Context myContext = DiabetesAddEvent.this.getApplicationContext();
            Toast.makeText(myContext, "The event was added",
                    Toast.LENGTH_LONG).show();
        }
    }





}
