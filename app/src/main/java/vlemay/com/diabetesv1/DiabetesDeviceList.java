package vlemay.com.diabetesv1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class DiabetesDeviceList extends Activity {

    static Intent listDevicesIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes_device_list);

        // listAllIntent.putExtra("listType","user");
        String data = getIntent().getExtras().getString("listType");

        //View rootView = inflater.inflate(R.layout.activity_diabetes_user_list, container, false);
        listDevicesIntent = new Intent(this,DiabetesListDevices.class);

        Log.i("jl", "into Diabetes Devices List");
        final Button listDeviceButton = (Button) findViewById(R.id.list_Device_button);
        final TextView deviceId =(TextView)findViewById(R.id.deviceIdText);

        listDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.i("jl", "clicked the device list button");
                String dId = deviceId.getText().toString();

                if(deviceId.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "A device Id is required",
                            Toast.LENGTH_LONG).show();

                }
                else {
                    listDevicesIntent.putExtra("listType", "device");

                    listDevicesIntent.putExtra("DeviceId", dId);

                    startActivity(listDevicesIntent);
                }

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.diabetes_device_list, menu);
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
