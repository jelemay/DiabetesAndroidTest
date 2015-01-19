package vlemay.com.diabetesv1.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Callable;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import vlemay.com.diabetesv1.Diabetes;
import vlemay.com.diabetesv1.R;
import vlemay.com.diabetesv1.client.PatientSvcApi;
import vlemay.com.diabetesv1.model.GlucoseEventData;
import vlemay.com.diabetesv1.service.PatientSvc;
import vlemay.com.diabetesv1.task.CallableTask;
import vlemay.com.diabetesv1.task.TaskCallback;

public class LoginScreenActivity extends Activity {

    @InjectView(R.id.username)
    protected EditText userName_;

    @InjectView(R.id.password)
    protected EditText password_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.loginButon)
    public void login() {
        String user = userName_.getText().toString();
        String pass = password_.getText().toString();
        String server = "https://vlemay.com:8443";


        final PatientSvcApi svc = PatientSvc.init(server, user, pass);

        // List<GlucoseEventData> getGlucoseEventList();

        CallableTask.invoke(new Callable<List<GlucoseEventData>>() {

            @Override
            public List<GlucoseEventData> call() throws Exception {
                return svc.getGlucoseEventList();
            }
        }, new TaskCallback<List<GlucoseEventData>>() {

            @Override
            public void success(List<GlucoseEventData> result) {
                // OAuth 2.0 grant was successful and we
                // can talk to the server, open up the video listing
                startActivity(new Intent(
                        LoginScreenActivity.this,
                        Diabetes.class));
            }

            @Override
            public void error(Exception e) {
                Log.e(LoginScreenActivity.class.getName(), "Error logging in via OAuth.", e);

                Toast.makeText(
                        LoginScreenActivity.this,
                        "Login failed, check your Internet connection and credentials.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_screen, menu);
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
