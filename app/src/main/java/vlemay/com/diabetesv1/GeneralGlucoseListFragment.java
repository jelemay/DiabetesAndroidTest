package vlemay.com.diabetesv1;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vlemay.com.diabetesv1.client.PatientSvcApi;
import vlemay.com.diabetesv1.model.GlucoseEventData;
import vlemay.com.diabetesv1.service.PatientSvc;

/**
 * Created by lemay on 10/22/14.
 */


public class GeneralGlucoseListFragment extends Fragment {

    static TextView list_header_Text_Box;
    static TextView size_of_list_Text_Box;
    static ListView glucose_list_ListView;
    //static Context myContext;

    static Context myContext;
    static String myOperation;
    public GeneralGlucoseListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_diabetes_list, container, false);
        Log.i("jl", "Got the root view");
        list_header_Text_Box = (TextView) rootView
                .findViewById(R.id.list_header_textView);
        size_of_list_Text_Box=(TextView) rootView
                .findViewById(R.id.sizeTextView);
        glucose_list_ListView=(ListView) rootView
                .findViewById(R.id.listViewAll);

        DiabetesList activity = (DiabetesList) getActivity();
        myContext = activity.getApplicationContext();

        Bundle b = getArguments();
        myOperation = b.getString("listType");
        Log.i("jl", "Got the operation");
        Log.i("jl", myOperation);


        Log.i("jl", "Got the context");
        String s =myContext.toString();
        Log.i("jl", s);

        Log.i("jl", "Got the list view");
        new LoadListTask().execute(1);

        return rootView;
    }


    class LoadListTask extends AsyncTask<Integer, Integer, List<GlucoseEventData>> {


        @Override
        protected void onPreExecute() {
            // mProgressBar.setVisibility(ProgressBar.VISIBLE);
            if (list_header_Text_Box != null) {
                Log.i("jl", "List view is not null");
                list_header_Text_Box.setText("Reading the List Data from the Network");
            } else {
                Log.i("jl", "List View is null");
            }

        }

        @Override
        protected List<GlucoseEventData> doInBackground(Integer... resId) {
            Log.i("jl", "Executing the async task");
            Log.i("jl", "Operation ID as seen in Async Task");
            Log.i("jl", myOperation);



            PatientSvcApi patientSvc = PatientSvc.getOrShowLogin(DiabetesList.myContext);

            List<GlucoseEventData> gList = patientSvc.getGlucoseEventList();

            String length = String.valueOf(gList.size());
            Log.i("jl", length);

            return gList;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // mProgressBar.setProgress(values[0]);
            // Log.i("jl", "Doing something with progress");

        }

        @Override
        protected void onPostExecute(List<GlucoseEventData> result) {
            int listSize=result.size();
            list_header_Text_Box.setText("Got the List Data from the Network");
            size_of_list_Text_Box.setText(String.valueOf(listSize));
            Log.i("jl", "Finished the task");

            Log.i("jl", "the result object is is");

            Log.i("jl",result.toString());


            ArrayList<String> glucoseEventList = new ArrayList<String>();

            for (GlucoseEventData ev : result) {
                String event = " ";
                long id = ev.getId();
                Log.i("jl", "got id");
                Date creationDate = ev.getCreationDate();
                Log.i("jl", "got creation date");
                Double concentration = ev.getConcentration();
                Log.i("jl", "got concentration");
                Boolean isBeforeMeal = ev.getIsBeforeMeal();
                if(isBeforeMeal==null) isBeforeMeal= false;
                Log.i("jl", "got is before meal");
                Boolean isAfterMeal = ev.getIsAfterMeal();
                Log.i("jl", "got is after meal");
                Long deviceID = ev.getDeviceId();
                Log.i("jl", "got device id");

                event = event +
                        "   Event Id =  " + Long.toString(id)+
                        "   Creation Date=  " + creationDate.toString()+
                       "   Concentration =   " + Double.toString(concentration)+
                        "   Is Before Meal=   " + Boolean.toString(isBeforeMeal)+
                        "   Is After Meal=   " + Boolean.toString(isAfterMeal) +
                       "   Device Id =   " + Long.toString(deviceID)
                      ;


                Log.i("jl", "an event  is");
                Log.i("jl", event);

                glucoseEventList.add(event);
                Log.i("jl", "an event was added to the ArrayList");
                Log.i("jl", glucoseEventList.toString());

            }
            Log.i("jl", "the glucoseEventList object and size");
            Log.i("jl", glucoseEventList.toString());
            Log.i("jl", String.valueOf(glucoseEventList.size()));

            Log.i("jl", "About to define array adapter");

            ArrayAdapter<String> arrayAdapter =
                   // new ArrayAdapter<String>(myContext, android.R.layout.simple_list_item_1, glucoseEventList);
                    new ArrayAdapter<String>(myContext, android.R.layout.simple_list_item_1, glucoseEventList);
            Log.i("jl", "the arrayAdapter object");
            Log.i("jl", arrayAdapter.toString());
            // Set The Adapter
            glucose_list_ListView.setAdapter(arrayAdapter);
            Log.i("jl", "set the arrayAdapter in the UI");


        }

        private void sleep() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
        }

    }
}









