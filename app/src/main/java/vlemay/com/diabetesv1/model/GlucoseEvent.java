package vlemay.com.diabetesv1.model;

import java.util.Date;


public class GlucoseEvent {


	private Long id;

	private double concentration;
	private Boolean isBeforeMeal;
	private Boolean isAfterMeal;
	private Long deviceId;

	private Date creationDate;

	private Patient patient;

	public GlucoseEvent() {
	}

//	public GlucoseEvent(double concentration, Boolean isBeforeMeal,
//			Boolean isAfterMeal, Long deviceId,Patient patient) {
//		this.concentration = concentration;
////		this.isBeforeMeal = isBeforeMeal;
//		this.isAfterMeal = isAfterMeal;
//		this.deviceId = deviceId;
	
//		this.creationDate = new Date();
//	}

	public GlucoseEvent(double concentration, Boolean isBeforeMeal,
			Boolean isAfterMeal, Long deviceId,  Patient patient) {
		this.concentration = concentration;
		this.isBeforeMeal = isBeforeMeal;
		this.isAfterMeal = isAfterMeal;
		this.deviceId = deviceId;
	
		this.creationDate = new Date();
		this.patient=patient;
	}

	public GlucoseEvent(GlucoseEventRequest geRequest) {
		// Get event attributes from HTTP Request
		this.concentration = geRequest.getConcentration();
		this.isBeforeMeal = geRequest.getIsBeforeMeal();
		this.isAfterMeal = geRequest.getIsAfterMeal();
		this.deviceId = geRequest.getDeviceId();

		this.creationDate = new Date();
	}
	
	public GlucoseEvent(GlucoseEventData geRequest) {
		// Get event attributes from HTTP Request
		this.concentration = geRequest.getConcentration();
		this.isBeforeMeal = geRequest.getIsBeforeMeal();
		this.isAfterMeal = geRequest.getIsAfterMeal();
		this.deviceId = geRequest.getDeviceId();
	
		this.creationDate = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	

}
