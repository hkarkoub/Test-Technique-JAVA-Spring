package com.meeting.planner.api.service;

import com.meeting.planner.api.model.MeetingDetails;
import com.meeting.planner.api.model.MeetingRoom;
import com.meeting.planner.api.repository.MeetingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * 
 * @author hkarkoub
 *
 */
@Service
public class MeetingRoomService {

	@Autowired
	private MeetingRoomRepository repository;

	public List<MeetingRoom> findAll() {
		return repository.findAll();
	}

	public Optional<MeetingRoom> findById(Long id) {
		return repository.findById(id);
	}

	public MeetingRoom save(MeetingRoom room) {
		return repository.save(room);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	
	//Find an available meeting room by type of the meeting and number of persons
	public List<MeetingRoom> findAvailableRoom(MeetingDetails meetingDetails) {

		List<String> equipments = new ArrayList<String>();
		if ("RS".equalsIgnoreCase(meetingDetails.getType())) {
			return repository.findAll().stream().filter(MeetingRoom::isAvailable)// Only available rooms
					.filter(room -> room.getCapacity() >= 3) // Filter by capacity
					.collect(Collectors.toList());
		} else if ("VC".equalsIgnoreCase(meetingDetails.getType()))

		{			

			equipments.add("Ecran");
			equipments.add("pieuvre");
			equipments.add("webcam");
		} else if ("SPEC".equalsIgnoreCase(meetingDetails.getType()))

		{
			equipments.add("tableau");

		} else if ("RC".equalsIgnoreCase(meetingDetails.getType()))

		{

			equipments.add("Ecran");
			equipments.add("pieuvre");

		} else
			{ 
			
				return null;
			
			}

		return repository.findAll().stream().filter(MeetingRoom::isAvailable)// Only available rooms
				.filter(MeetingRoom::isAvailable) // Only available rooms
				.filter(room -> room.getCapacity() >= meetingDetails.getNumberOfPersons()) // Filter by capacity
				.filter(room -> room.getEquipments().stream()
		                .map(equipment -> equipment.getName().toLowerCase())
		                .collect(Collectors.toSet())
		                .containsAll(equipments.stream().map(String::toLowerCase).collect(Collectors.toSet())))  // Filter by equipment
		            .collect(Collectors.toList());
	}
}