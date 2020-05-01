package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.WorkingOn;

import java.util.List;


@Repository
public interface WorkingOnRepository extends JpaRepository<WorkingOn, Integer> {

    @Query(value="SELECT * FROM working_on WHERE working_on.emp_id=?1 AND working_on.project_id=?2",     //Bahaa Projects
            nativeQuery = true)
    WorkingOn findWorkingOnByEmpIdAndProjectId(int emp_id , int project_id);

    @Query(value="SELECT * FROM working_on WHERE working_on.project_id=?1",                             //Bahaa Projects
            nativeQuery = true)
    List<WorkingOn> findWorkingOnsOfProjectBasedOnProjectId(int project_id);



    @Query(value="SELECT * FROM working_on WHERE working_on.emp_id=?1",  //Omar
            nativeQuery = true)
    List<WorkingOn> findByEmp_id(int emp_id);




}
