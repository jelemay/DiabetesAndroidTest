package vlemay.com.diabetesv1.model;

import java.util.Date;

public class GlucoseEventData {
	
	private Long id;


	private double concentration;
	private Boolean isBeforeMeal;
	private Boolean isAfterMeal;
	private Long deviceId;

	private Date creationDate;
	
	public GlucoseEventData(){}

	public GlucoseEventData(Long id, double concentration,
			Boolean isBeforeMeal, Boolean isAfterMeal, Long deviceId,
			 Date creationDate) {
		super();
		this.id = id;
		this.concentration = concentration;
		this.isBeforeMeal = isBeforeMeal;
		this.isAfterMeal = isAfterMeal;
		this.deviceId = deviceId;
		this.creationDate = creationDate;
	}
	
	public GlucoseEventData(GlucoseEvent event) {
		super();
		this.id = event.getId();
		this.concentration = event.getConcentration();
		this.isBeforeMeal = event.getIsBeforeMeal();
		this.isAfterMeal = event.getIsAfterMeal();
		this.deviceId = event.getDeviceId();
		this.creationDate = event.getCreationDate();
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

}
