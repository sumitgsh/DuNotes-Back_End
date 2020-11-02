package com.project.website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.website.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	
	@Query("Select m from Department m where m.department=?1")
	List<Department> findByDepartment(String department);
	

}
