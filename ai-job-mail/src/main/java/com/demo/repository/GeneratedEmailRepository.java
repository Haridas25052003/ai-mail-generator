package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.GeneratedEmail;


public interface GeneratedEmailRepository extends JpaRepository<GeneratedEmail,Integer>{

	List<GeneratedEmail> findByEmailRequest_User_Id(int userId);
}
