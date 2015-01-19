package vlemay.com.diabetesv1.model;

import java.util.ArrayList;
import java.util.Collection;

public class DiabetesUser {
	

	private Long id;
	
	private  ArrayList<String> authorities_;
	private  String password;
	private  String username;
	private  boolean enabled;
	private  boolean accountNonExpired;
	private  boolean accountNonLocked;
	private  boolean credentialsNonExpired;	
	

	
	public DiabetesUser(){
		
	}
	
	public DiabetesUser(String username, String password,
			boolean enabled,boolean accountNonExpired,
			boolean accountNonLocked,boolean credentialsNonExpired,
			ArrayList<String> authorities){
	
		this.username = username;
		this.password = password;
		authorities_ = authorities;
		this.enabled=enabled;
		this.accountNonExpired= accountNonExpired;
		this.accountNonLocked=accountNonLocked;
		this.credentialsNonExpired=credentialsNonExpired;
		
	}

	public Collection<String> getAuthorities() {
		return authorities_;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
	

	
	public void setAuthorities(ArrayList<String>  authorities){
		authorities_ = authorities;
	}
	public void setUsername(String username){
		this.username=username;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		 this.credentialsNonExpired=credentialsNonExpired;
	}
	public boolean  getCredentialsNonExpired() {
		 return credentialsNonExpired;
	}
	
	public void setAccountNonLocked(boolean accountNonLocked) {
		 this.accountNonLocked=accountNonLocked;
	}
	public boolean  getAccountNonLocked() {
		 return accountNonLocked;
	}
	
	
	public void setAccountNonExpired(boolean accountNonExpired) {
		 this.accountNonExpired=accountNonExpired;
	}
	public boolean  getAccountNonExpired() {
		 return accountNonExpired;
	}
	
	public void setEnabled(boolean enabled) {
		 this.enabled=enabled;
	}
	public boolean  getEnabled() {
		 return enabled;
	}
	


}
