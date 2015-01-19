package vlemay.com.diabetesv1.model;

public class GlucoseEventRequest {

	private double concentration;
	private Boolean isBeforeMeal;
	private Boolean isAfterMeal;
	private Long deviceId;


	
	public GlucoseEventRequest(){}
	
	public GlucoseEventRequest(double concentration,Boolean isBeforeMeal,Boolean isAfterMeal,
			Long deviceId
		
			){
		
		this.concentration=concentration;
		this.isBeforeMeal=isBeforeMeal;
		this.isAfterMeal=isAfterMeal;
		this.deviceId=deviceId;
		
	

	}
	
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


	
}
