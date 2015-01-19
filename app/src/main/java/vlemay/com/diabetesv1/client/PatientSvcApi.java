package vlemay.com.diabetesv1.client;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import vlemay.com.diabetesv1.model.GlucoseEventData;
import vlemay.com.diabetesv1.model.GlucoseEventRequest;


public interface PatientSvcApi {
	
public static final String TITLE_PARAMETER = "title";
	
	public static final String PASSWORD_PARAMETER = "password";

	public static final String USERNAME_PARAMETER = "username";

	public static final String DURATION_PARAMETER = "duration";
	
	public static final String TOKEN_PATH = "/oauth/token";


	
	  // The path where we expect the DiabetesSvc to live
    public static final String Patient_SVC_PATH = "/patient";

    @GET(Patient_SVC_PATH+"/glucose")
    public List<GlucoseEventData> getGlucoseEventList();
    
    @GET(Patient_SVC_PATH+"/glucose/device/{deviceID}")
    public List<GlucoseEventData> getGlucoseEventListByDeviceId(@Path("deviceID") long deviceId);

    @GET(Patient_SVC_PATH+"/test")
    public String  patientTest();
    
    @PUT(Patient_SVC_PATH+"/glucose")
    public GlucoseEventData addGlucoseEvent(@Body GlucoseEventRequest g);
    
    @DELETE(Patient_SVC_PATH+"/glucose/{glucoseID}")
    public boolean deleteGlucoseEvent(@Path("glucoseID") long glucoseId);
    
    @DELETE(Patient_SVC_PATH+"/glucose")
    public boolean deleteAllGlucoseEvents();
    
	
    
   



}
