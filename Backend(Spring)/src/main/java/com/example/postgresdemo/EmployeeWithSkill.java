package com.example.postgresdemo;

import com.example.postgresdemo.model.Employee;
import com.example.postgresdemo.model.Skill;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmployeeWithSkill {                      //Added Bahaa
    public Employee getEmployeedata() {
		return employeedata;
	}
	public void setEmployeedata(Employee employeedata) {
		this.employeedata = employeedata;
	}
	public Skill getSkilldata() {
		return skilldata;
	}
	public void setSkilldata(Skill skilldata) {
		this.skilldata = skilldata;
	}
	private Employee employeedata;
    private Skill skilldata;

}
