package vlemay.com.diabetesv1.model;

import java.util.Collection;


public class PatientRequest {
	
	//TODO make this scalable
	

	
	private DiabetesUser diabetesUser;
	
	private Collection<GlucoseEvent> glucoseEvents;
	
	public PatientRequest(DiabetesUser diabetesUser,Collection<GlucoseEvent> glucoseEvents){
		
		this.diabetesUser=diabetesUser;
		this.glucoseEvents=glucoseEvents;
		
	}
	public void  setDiabetesUser(DiabetesUser diabetesUser){
		this.diabetesUser=diabetesUser;
	}
	
	public DiabetesUser getDiabetesUser(){
		return diabetesUser;
	}
	
	public void setGlucoseEvents(Collection<GlucoseEvent> glucoseEvents){
		this.glucoseEvents=glucoseEvents;
	}
	
	public Collection<GlucoseEvent> getGlucoseEvents(){
		return glucoseEvents;
	}

}
