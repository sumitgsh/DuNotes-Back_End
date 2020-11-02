package com.project.website.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.website.model.DeptProgrammes;
import com.project.website.model.Media;

public interface DeptProgRepositiory extends JpaRepository<DeptProgrammes, Integer> {

	
	//SELECT all the elments that belong to a particular degree
	 @Query("select m from DeptProgrammes m where m.degreeName=?1")
	 Optional<DeptProgrammes> findByProgramme(String degreeName);
	 
//	//Show the programmes when the department is enabled
//	 @Query("Select m from DeptProgrammes m left join m.department")
//	 Optional<DeptProgrammes> findAllEnabledProgrammes();
//	 
	
}
