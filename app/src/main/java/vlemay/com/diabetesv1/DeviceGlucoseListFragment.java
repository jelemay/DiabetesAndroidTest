package vlemay.com.diabetesv1;

/**
 * Created by lemay on 10/26/14.
 */

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
 * Created by lemay on 10/23/14.
 */
public class DeviceGlucoseListFragment extends Fragment{

    static TextView list_header_Text_Box;
    static TextView size_of_list_Text_Box;
    static ListView glucose_list_ListView;
    //static Context myContext;

    static Context myContext;
    static String myOperation;
    static String myDeviceId;
    static Long myDeviceIdL;

    public DeviceGlucoseListFragment() {
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

        DiabetesListDevices activity = (DiabetesListDevices) getActivity();
        myContext = activity.getApplicationContext();

        Bundle b = getArguments();
        myOperation = b.getString("listType");
        Log.i("jl", "Got the operation");
        Log.i("jl", myOperation);
        myDeviceId = b.getString("DeviceId");
        myDeviceIdL =Long.valueOf(myDeviceId);



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
            Log.i("jl", "User ID as seen in Async Task");
            Log.i("jl", myDeviceId);

            PatientSvcApi patientSVC = PatientSvc.getOrShowLogin(myContext);




            List<GlucoseEventData> gList = patientSVC.getGlucoseEventListByDeviceId(myDeviceIdL);

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

            ArrayList<String> glucoseEventList = new ArrayList<String>();

            for (GlucoseEventData ev : result) {
                String event = " ";
                long id = ev.getId();
                Date creationDate = ev.getCreationDate();
                Double concentration = ev.getConcentration();
                Boolean isBeforeMeal = ev.getIsBeforeMeal();
                if(isBeforeMeal == null) isBeforeMeal=true;
                Boolean isAfterMeal = ev.getIsAfterMeal();
                if(isAfterMeal == null) isAfterMeal=true;
                Long deviceID = ev.getDeviceId();

                event = event +
                        "   Event Id =  " + Long.toString(id)+
                        "   Creation Date=  " + creationDate.toString()+
                        "   Concentration =   " + Double.toString(concentration) +
                        "   Is Before Meal=   " + Boolean.toString(isBeforeMeal) +
                        "   Is After Meal=   " + Boolean.toString(isAfterMeal) +
                        "   Device Id =   " + Long.toString(deviceID) ;
                glucoseEventList.add(event);

            }



            ArrayAdapter<String> arrayAdapter =
                    // new ArrayAdapter<String>(myContext, android.R.layout.simple_list_item_1, glucoseEventList);
                    new ArrayAdapter<String>(myContext, android.R.layout.simple_list_item_1, glucoseEventList);

            // Set The Adapter
            glucose_list_ListView.setAdapter(arrayAdapter);


        }

        private void sleep() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
        }

    }
}


