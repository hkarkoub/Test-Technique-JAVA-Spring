package com.meeting.planner.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class MeetingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private int capacity;
    
    
    private boolean available;
    
    @ManyToMany
    @JoinTable(
      name = "meetingroom_equipment",
      joinColumns = @JoinColumn(name = "meetingroom_id"),
      inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private List<Equipment> equipments;
   
	//Getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
	 public List<Equipment> getEquipments() {
			return equipments;
		}
		public void setEquipments(List<Equipment> equipments) {
			this.equipments = equipments;
		}
    
    
    
}