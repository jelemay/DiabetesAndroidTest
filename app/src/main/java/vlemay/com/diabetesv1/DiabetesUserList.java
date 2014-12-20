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


public class DiabetesUserList extends Activity {
    static Intent listUsersIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes_user_list);

        // listAllIntent.putExtra("listType","user");
        String data = getIntent().getExtras().getString("listType");


        //View rootView = inflater.inflate(R.layout.activity_diabetes_user_list, container, false);
        listUsersIntent = new Intent(this,DiabetesListUsers.class);

        Log.i("jl", "into Diabetes User List");
        final Button listUserButton = (Button) findViewById(R.id.list_User_button);
        final TextView userId =(TextView)findViewById(R.id.userIdText);

    listUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.i("jl", "clicked the user list button");
                String uId = userId.getText().toString();
            ;
                if(userId.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "A user Id is required",
                            Toast.LENGTH_LONG).show();

                }
                else {
                    listUsersIntent.putExtra("listType", "user");

                    listUsersIntent.putExtra("UserId", uId);

                    startActivity(listUsersIntent);
                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.diabetes_user_list, menu);
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
