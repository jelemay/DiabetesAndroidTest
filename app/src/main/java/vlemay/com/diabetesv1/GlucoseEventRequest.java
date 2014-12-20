package vlemay.com.diabetesv1;

/**
 * Created by lemay on 10/28/14.
 */

public class GlucoseEventRequest {

    private double concentration;
    private Boolean isBeforeMeal;
    private Boolean isAfterMeal;
    private Long deviceId;
    private Long userId;

  /*  GlucoseEventRequest(double concentration,Boolean isBeforeMeal,Boolean isAfterMeal,Long deviceId,Long userId ){
        this.concentration = concentration;
        this.isBeforeMeal = isBeforeMeal;
        this.isAfterMeal = isAfterMeal;
        this.deviceId = deviceId;
        this.userId = userId;
    }
*/
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

}
