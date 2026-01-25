package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.EmailRequest;
import com.demo.model.GeneratedEmail;
import com.demo.model.User;
import com.demo.repository.EmailRequestRepository;
import com.demo.repository.GeneratedEmailRepository;

@Service
public class EmailService {

    @Autowired
    private EmailRequestRepository emailRequestRepository;

    @Autowired
    private GeneratedEmailRepository generatedEmailRepository;

    public EmailRequest saveRequest(
            String position,
            String company,
            String jd,
            User user) {

        EmailRequest req = new EmailRequest();
        req.setPosition(position);
        req.setCompany(company);
        req.setJobDescription(jd);
        req.setUser(user);

        return emailRequestRepository.save(req);
    }

    public GeneratedEmail saveGeneratedEmail(
            String subject,
            String body,
            EmailRequest request) {

        GeneratedEmail email = new GeneratedEmail();
        email.setSubject(subject);
        email.setBody(body);
        email.setEmailRequest(request);

        return generatedEmailRepository.save(email);
    }
}
