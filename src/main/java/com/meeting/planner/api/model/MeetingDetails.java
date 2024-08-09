package com.meeting.planner.api.model;

public class MeetingDetails {

	
	private String name;
	private String type;
	private int	   numberOfPersons;
	
	
	//Getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumberOfPersons() {
		return numberOfPersons;
	}
	public void setNumberOfPersons(int numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}
	
}
