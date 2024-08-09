package com.meeting.planner.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meeting.planner.api.model.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
	//TO DO
}