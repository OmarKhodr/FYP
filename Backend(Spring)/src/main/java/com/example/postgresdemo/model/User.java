package com.example.postgresdemo.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name = "Users")

public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emp_id;

    private String UserName;
    
    private String Password;
    
    public User () {
    	this.UserName="user";
    	this.Password="pass";
    }
    
    
    public User (String UserName,String Password) {
    	this.UserName=UserName;
    	this.Password=Password;
    }
	

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

}
