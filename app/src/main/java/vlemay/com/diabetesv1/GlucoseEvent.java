package vlemay.com.diabetesv1;
import java.util.Date;
/**
 * Created by lemay on 10/15/14.
 *
 */
public class GlucoseEvent {

    private Long id;
    private double concentration;
    private Boolean isBeforeMeal;
    private Boolean isAfterMeal;
    private Long deviceId;
    private Long userId;
    private Date creationDate;

    public GlucoseEvent(double concentration,Boolean isBeforeMeal,Boolean isAfterMeal,long deviceId, long userId){
        this.concentration=concentration;
        this.isBeforeMeal=isBeforeMeal;
        this.isAfterMeal=isAfterMeal;
        this.deviceId=deviceId;
        this.userId=userId;
        //this.creationDate=new Date();

    }
/*
    public GlucoseEvent(GlucoseEventRequest geRequest) {
        // Get event attributes from HTTP Request
        this.concentration = geRequest.getConcentration();
        this.isBeforeMeal = geRequest.getIsBeforeMeal();
        this.isAfterMeal = geRequest.getIsAfterMeal();
        this.deviceId = geRequest.getDeviceId();
        this.userId = geRequest.getUserId();
        this.creationDate = new Date();
    }

*/


    public GlucoseEvent(){}
    public double getConcentration() {
        return concentration;
    }
    public void setConcentration(double concentration) {
        this.concentration = concentration;
    }

    public Boolean getIsBeforeMeal() {
        return isBeforeMeal;
    }
    public void setIsBeforeMeal(Boolean isBeforeMeal) {
        this.isBeforeMeal = isBeforeMeal;
    }

    public Boolean getIsAfterMeal() {
        return isAfterMeal;
    }
    public void setIsAfterMeal(Boolean isAfterMeal) {
        this.isAfterMeal = isAfterMeal;
    }

    public Long getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreationDate(){ return creationDate ;}
    public void setCreationDate(Date creationDate){this.creationDate=creationDate;}

    public Long getId(){return id; }
    public void setId(Long id){this.id=id;}






}





