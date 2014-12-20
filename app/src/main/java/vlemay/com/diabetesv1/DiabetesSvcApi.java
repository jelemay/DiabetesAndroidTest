package vlemay.com.diabetesv1;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by lemay on 10/15/14.
 */


public interface DiabetesSvcApi {

    // The path where we expect the DiabetesSvc to live
    public static final String Diabetes_SVC_PATH = "/glucose";

    @GET(Diabetes_SVC_PATH)
    public List<GlucoseEvent> getGlucoseList();

    @GET(Diabetes_SVC_PATH+"/device/{deviceID}")
    public List<GlucoseEvent> getGlucoseEventListByDeviceId(@Path("deviceID") long deviceId);

    @GET(Diabetes_SVC_PATH+"/user/{userID}")
    public List<GlucoseEvent> getGlucoseEventListByUserId(@Path("userID") long userId);

    @POST(Diabetes_SVC_PATH+"/{glucoseID}")
    public GlucoseEvent updateGlucoseEvent(@Path("glucoseID") long glucoseId, @Body GlucoseEvent g);
    //
    // the okhttp libraries are needed in gradle to fix the HTTP put failures that occur in retrofit
    // this isn't obvious and was discovered from google after trying to fix the code
    @PUT(Diabetes_SVC_PATH)
    public GlucoseEvent addGlucoseEvent(@Body GlucoseEventRequest g);


    @DELETE(Diabetes_SVC_PATH+"/{glucoseID}")
    public boolean deleteGlucoseEvent(@Path("glucoseID") long glucoseId);

    @GET(Diabetes_SVC_PATH+"/test")
    public String  testGlucose();



}
