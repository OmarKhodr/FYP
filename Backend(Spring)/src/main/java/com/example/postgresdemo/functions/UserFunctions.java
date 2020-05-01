package com.example.postgresdemo.functions;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.LoginInterface;
import com.example.postgresdemo.model.Project;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.repository.ProjectRepository;
import com.example.postgresdemo.repository.UserRepository;


@RestController

public class UserFunctions {

	
	@Autowired
    private UserRepository userRepository;
	
	@GetMapping("/users")
    public Page<User> getQuestions(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
	
	@CrossOrigin
	@PostMapping("/users/SignUp")
	public boolean SignUp (@RequestBody LoginInterface user) {
		boolean decider=true;
		//if(user.getPassword().equals(user.getRepeatedPassword())) {
		if(user.getUsername()==null || user.getPassword()==null || user.getRepeatedPassword()==null || !user.getPassword().equals(user.getRepeatedPassword())) {
			System.out.println(user.getUsername()+" "+user.getPassword()+" "+user.getRepeatedPassword());
			return false;
		}
		else {
			
		
			List<User> temp=userRepository.findAll();
			for(int i=0;i<temp.size();i++) {
				if(temp.get(i).getUserName().equals(user.getUsername())) {
					decider=false;
				}
			}
		
			if(decider==true) {
				userRepository.save(new User(user.getUsername(),user.getPassword()));
				System.out.println(true);
				return true;
			}
			else {
				System.out.println(false);
				return false;
			}
		}
	}
		
	
	@CrossOrigin
	@PostMapping("/users/Login")
	public boolean Login (@RequestBody User user) {
		boolean decider=false;
		List<User> temp=userRepository.findAll();
		for(int i=0;i<temp.size();i++) {
			if(temp.get(i).getUserName().equals(user.getUserName()) & temp.get(i).getPassword().equals(user.getPassword())) {
				decider=true;
				break;
			}
		}
		if(decider==true) {
			System.out.println(true);
			return true;
		}
		else {
			System.out.println(false);
			return false;
		}
	}
	@DeleteMapping("/users/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Integer questionId) {
        return userRepository.findById(questionId)
                .map(question -> {
                    userRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }
}