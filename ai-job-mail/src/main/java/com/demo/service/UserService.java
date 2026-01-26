package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.User;
import com.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getOrCreateUser(String name,String email) {
		
		User existing = userRepository.findByEmail(email);
		
		if(existing !=null) {
			return existing;
		}
		
		User user=new User();
		
		user.setName(name);
		user.setEmail(email);
		
		return userRepository.save(user);
	}

//	public User getUserById(Long userId) {
//		
//		return userRepository.findById(userId);
//	}
	
//     List<User> GetUserById(Long id) {
//		return userRepository.getById(id);
//	}
	
	public User getUserById(int id) {
        return userRepository.findById(id);
                
    }
	
	//register
	public User register(String name,String email) {
		User existing=userRepository.findByEmail(email);
		
		if(existing!=null) {
			throw new RuntimeException("user already registered");
		}
		
		User user=new User();
		user.setName(name);
		user.setEmail(email);
		
		return userRepository.save(user);
		
	}
	

}
