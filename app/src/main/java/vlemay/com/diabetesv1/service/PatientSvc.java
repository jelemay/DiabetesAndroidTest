package vlemay.com.diabetesv1.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit.RestAdapter;
import retrofit.client.ApacheClient;
import retrofit.converter.GsonConverter;
import vlemay.com.diabetesv1.client.EasyHttpClient;
import vlemay.com.diabetesv1.client.PatientSvcApi;
import vlemay.com.diabetesv1.client.SecuredRestBuilder;
import vlemay.com.diabetesv1.login.LoginScreenActivity;

/**
 * Created by lemay on 1/17/15.
 */
public class PatientSvc {

    public static final String CLIENT_ID = "mobile";

    private static PatientSvcApi patientSvc_;

    public static synchronized PatientSvcApi getOrShowLogin(Context ctx) {
        if (patientSvc_ != null) {
            return patientSvc_;
        } else {
            Intent i = new Intent(ctx, LoginScreenActivity.class);
            ctx.startActivity(i);
            return null;
        }
    }

    public static synchronized PatientSvcApi init(String server, String user,
                                                String pass) {
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            {
                String date = json.getAsJsonPrimitive().getAsString();
                long dateL = Long.valueOf(date);

                Log.i("jl", "Date long to string");
                Log.i("jl", String.valueOf(dateL));

                Date dateReturn = new Date(dateL);

                return dateReturn;
            }
        });

        Gson gson = builder.create();




        patientSvc_ = new SecuredRestBuilder()
                .setLoginEndpoint(server + PatientSvcApi.TOKEN_PATH)
                .setConverter(new GsonConverter(gson))
                .setUsername(user)
                .setPassword(pass)
                .setClientId(CLIENT_ID)
                .setClient(
                        new ApacheClient(new EasyHttpClient()))
                .setEndpoint(server).setLogLevel(RestAdapter.LogLevel.FULL).build()
                .create(PatientSvcApi.class);

        return patientSvc_;
    }




}
