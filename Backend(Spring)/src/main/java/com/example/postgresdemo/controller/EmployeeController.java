package com.example.postgresdemo.controller;

import com.example.postgresdemo.EmployeeWithSkill;
import com.example.postgresdemo.EmployeeWithSkills;
import com.example.postgresdemo.exception.ResourceNotFoundException;

import com.example.postgresdemo.model.Employee;
import com.example.postgresdemo.model.Skill;
import com.example.postgresdemo.repository.EmployeeRepository;

import com.example.postgresdemo.repository.SkillRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Data
@Setter
@Getter

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmployeeController {

    @Autowired
    private EmployeeRepository questionRepository;

    @Autowired                                 //Added Bahaa
    private SkillRepository skillrepo;

    @CrossOrigin
    @GetMapping("/employees")
    public Page<Employee> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @CrossOrigin
    @GetMapping("/employees/getemployeeskills/{employeeid}")                                       //Exclusive Bahaa
    public List<Skill> getSkillsOfEmployeeBasedonEmpId(@PathVariable("employeeid") int employeeid){
        return skillrepo.findSkillsOfEmployeeBasedOnEmpId(employeeid);
    }


    @CrossOrigin
    @PostMapping("/employees")
    public Employee createQuestion(@Valid @RequestBody Employee question) {
        return questionRepository.save(question);
    }

    @CrossOrigin
    @PostMapping("/employees/withskill")                                                         //Added Bahaa
    public Employee createEmployeeWithSkill(@Valid @RequestBody EmployeeWithSkill EmpAndSkill){
        EmpAndSkill.getSkilldata().setEmp_id(EmpAndSkill.getEmployeedata());

        questionRepository.save(EmpAndSkill.getEmployeedata());
        skillrepo.save(EmpAndSkill.getSkilldata());
        return questionRepository.getOne(EmpAndSkill.getEmployeedata().getEmp_id());

    }

    @CrossOrigin
    @PostMapping("/employees/withskills")                                                       //Added Bahaa
    public Employee createEmployeeWithSkills (@Valid @RequestBody EmployeeWithSkills EmpAndSkills){
        questionRepository.save(EmpAndSkills.getEmployeedata());

        int skillslength= EmpAndSkills.getSkills().length;
        Skill skillList[]= EmpAndSkills.getSkills();

        for ( int i=0; i<skillslength; i++){
            skillList[i].setEmp_id(EmpAndSkills.getEmployeedata());
            skillrepo.save(skillList[i]);
        }
        return questionRepository.getOne(EmpAndSkills.getEmployeedata().getEmp_id());

    }

    @CrossOrigin
    @PostMapping("/skills/{employeeid}")                                                      //Added Bahaa
    public Employee addSkillToEmployee (@PathVariable("employeeid") Integer employeeid,@Valid @RequestBody Skill s){
        Employee employeeOfSkill = questionRepository.getOne(employeeid);
        s.setEmp_id(employeeOfSkill);
        skillrepo.save(s);

        return questionRepository.getOne(employeeid);


    }

    @CrossOrigin
    @DeleteMapping("/skills/{employeeid}/{skill}")                                            //Added Bahaa
    public ResponseEntity<?> DeleteSkillOfEmployee(@PathVariable("employeeid") int employeeid,@PathVariable String skill) {

        try {
            Skill skilltodelete = skillrepo.findSkillBySkillNameAndEmpId(employeeid, skill);


            skillrepo.delete(skilltodelete);

        }
            catch (IllegalArgumentException e){
               throw new ResourceNotFoundException("Skill "+skill+" not found with employee id: "+employeeid);
            }
            return ResponseEntity.ok().build();


    }

    @CrossOrigin
    @PutMapping("/skills/{employeeid}/{skill}/{category}")                                                                //Added Bahaa -- post authorization
    public Skill updateSkillofEmployee(@PathVariable("employeeid") int employeeid,@PathVariable("skill") String skill, @PathVariable("category") String category,
                                       @Valid @RequestBody Skill s){

        try{
            Skill skilltoupdate =  skillrepo.findSkillBySkillNameAndEmpIdAndCategory(employeeid,skill,category);
            skilltoupdate.setCompetency(s.getCompetency());
            return skillrepo.save(skilltoupdate);
        }

        catch (IllegalArgumentException e){
            throw new ResourceNotFoundException("Skill "+skill+" not found with employee id: "+employeeid);
        }

    }

    @CrossOrigin
    @PutMapping("/employees/{questionId}")
    public Employee updateQuestion(@PathVariable Integer questionId,
                                   @Valid @RequestBody Employee questionRequest) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    question.setFirst_name(questionRequest.getFirst_name());
                    question.setLast_name(questionRequest.getLast_name());
                    question.setPosition(questionRequest.getPosition());
                    question.setDepartment(questionRequest.getDepartment());
                    question.setNationality(questionRequest.getNationality());
                    question.setEffectiveness(questionRequest.getEffectiveness());
                    question.setEfficiency(questionRequest.getEfficiency());
                    question.setGender(questionRequest.getGender());
                    question.setHire_date(questionRequest.getHire_date());
                    question.setBranch_id(questionRequest.getBranch_id());
                    question.setPhone_num(questionRequest.getPhone_num());
                    question.setPhone_num(questionRequest.getPhone_num());
                    return questionRepository.save(question);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }


    @CrossOrigin
    @DeleteMapping("/employees/{questionId}")                                                 //updated Bahaa
    public ResponseEntity<?> deleteQuestion(@PathVariable Integer questionId) {

        List<Skill> skillstodelete = skillrepo.findSkillsOfEmployeeBasedOnEmpId(questionId);       //beginning of added part

        for(Skill s: skillstodelete){
            skillrepo.delete(s);
        }                                                                                          //end of added part


        return questionRepository.findById(questionId)
                .map(question -> {
                    questionRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }

//    @RequestMapping("/employees/getbyfnandpos/{first_name}/{position}")
//        public List<Employee> queryEmployeeByfnAndpos(@PathVariable("first_name") String first_name, @PathVariable("position") String position)
//    {
//        System.out.println(" "+ questionRepository.findByfirstnameAndPosition(first_name, position));
//        return questionRepository.findByfirstnameAndPosition(first_name,position);
//
//
//    }

    @CrossOrigin
    @RequestMapping("/employees/skillandlevel/{skill}/{competency}")                                                      //Added Bahaa
    public List<Employee> queryEmployeeBySkillandCompetency(@PathVariable("skill") String skill, @PathVariable("competency") float competency)
    {
        return questionRepository.findBySkillandCompetency(skill,competency);
    }

}
