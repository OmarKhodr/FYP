package com.example.postgresdemo.controller;

import javax.validation.Valid;


import com.example.postgresdemo.model.Employee;
import com.example.postgresdemo.model.Skill;
import com.example.postgresdemo.model.WorkingOn;
import com.example.postgresdemo.repository.EmployeeRepository;
import com.example.postgresdemo.repository.WorkingOnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Project;
import com.example.postgresdemo.repository.ProjectRepository;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@RestController

public class ProjectController {

    @Autowired
    private ProjectRepository questionRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private WorkingOnRepository workingonrepo;                                                    //Bahaa projects

    @CrossOrigin
    @GetMapping("/projects")
    public Page<Project> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @CrossOrigin
    @PostMapping("/projects")
    public Project createQuestion(@Valid @RequestBody Project question) {
        return questionRepository.save(question);
    }

    @CrossOrigin
    @PutMapping("/projects/{questionId}")
    public Project updateQuestion(@PathVariable Integer questionId,
                                   @Valid @RequestBody Project questionRequest) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    question.setProject_name(questionRequest.getProject_name());
                    question.setProject_leader(questionRequest.getProject_leader());
                    question.setCompleted(questionRequest.getCompleted());
                    question.setStart_date(questionRequest.getStart_date());
                    question.setEnd_date(questionRequest.getEnd_date());
                    question.setEffectiveness(questionRequest.getEffectiveness());
                    question.setEfficiency(questionRequest.getEfficiency());
                    question.setDeadline(questionRequest.getDeadline());
                    question.setSeverity(questionRequest.getSeverity());
                    return questionRepository.save(question);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }


    @CrossOrigin
    @DeleteMapping("/projects/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Integer questionId) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    questionRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }

//    @CrossOrigin                                                                    //Bahaa projects
//    @PostMapping("/workingon")
//    public WorkingOn createWorkingOn(@Valid @RequestBody WorkingOn body) {
//        return workingonrepo.save(body);
//    }

    @CrossOrigin
    @PostMapping("/workingon/{employeeid}/{projectid}")                                                      //Added Bahaa
    public Employee addWorkingOnToProject (@PathVariable("employeeid") Integer employeeid, @PathVariable("projectid") Integer projectid, @Valid @RequestBody WorkingOn w){
        Employee employeeOfWorkingOn = employeeRepository.getOne(employeeid);
        Project projectOfWorkingOn = questionRepository.getOne(projectid);
        w.setEmp_id(employeeOfWorkingOn);
        w.setProject_id(projectOfWorkingOn);

        workingonrepo.save(w);

        return employeeRepository.getOne(employeeid);



    }

    @CrossOrigin                                                                    //Bahaa projects
    @DeleteMapping("/workingon/{employeeid}/{projectid}")
    public ResponseEntity<?> DeleteWorkingOnofProject(@PathVariable("employeeid") int employeeid, @PathVariable("projectid") int projectid) {
        try {
            WorkingOn workingontodelete = workingonrepo.findWorkingOnByEmpIdAndProjectId(employeeid, projectid);
            workingonrepo.delete(workingontodelete);
        }

        catch (IllegalArgumentException e){
            throw new ResourceNotFoundException("WorkingOn Not Found");
        }
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @GetMapping("/workingon/{projectid}")                                         //Bahaa projects
    public List<WorkingOn> GetWorkingOnOfProjectBasedOnProjectId(@PathVariable("projectid") int projectid){
        return workingonrepo.findWorkingOnsOfProjectBasedOnProjectId(projectid);
    }




}
