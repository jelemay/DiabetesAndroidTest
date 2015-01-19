package vlemay.com.diabetesv1.model;


import java.util.Collection;


public class Patient {
	

	

	private Long id;
	

	private DiabetesUser diabetesUser;

	private Collection<GlucoseEvent> glucoseEvents;
	
	public Patient(){
		
	}
	
//	public Patient(DiabetesUser user){
//		diabetesUser=user;
//	}
	
	public Patient(DiabetesUser user, Collection<GlucoseEvent> glucoseEvents){
		diabetesUser=user;
		this.glucoseEvents=glucoseEvents;
	}
	
	public DiabetesUser getDiabetesUser(){
		return diabetesUser;
	}
	
	public void serDiabetesUser(DiabetesUser user){
		diabetesUser=user;	}
	

	
	public Collection<GlucoseEvent> getGlucoseEvents(){
		return glucoseEvents;
	}
	public void setGlucoseEvents(Collection glucoseEvents){
		this.glucoseEvents=glucoseEvents;
	}
	public Long getId(){
		return id;
	}

}
