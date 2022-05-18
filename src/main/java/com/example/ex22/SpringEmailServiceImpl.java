package com.example.ex22;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SpringEmailServiceImpl implements SpringEmailService {

    @Value("${mail.smtp.host}")
    private String host; 

    @Value("${mail.from}")
    private String from;

    @Value("${mail.rcpt}")
    private String rcpt;

    private JavaMailSender sender;
    
    @PostConstruct
    private void init() {
	    JavaMailSenderImpl s = new JavaMailSenderImpl();
	    s.setHost(host);
	    // s.setPort(25); // default ?
	     
	    // s.setUsername("admin@gmail.com");
	    // s.setPassword("password");
	     
	    Properties p = s.getJavaMailProperties();
	    p.put("mail.transport.protocol", "smtp");
	    // p.put("mail.smtp.auth", "true");
	    // p.put("mail.smtp.starttls.enable", "true");
	    // p.put("mail.debug", "true");

        sender = s;
	}

    @Async
    @Override
    public void sendMail(String subj, String msg) 
    {
        long t = Thread.currentThread().getId();
        log.info("Email service (org.springframework.mail.javamail.JavaMailSender) on thread {} sendEmail( {} )", t, msg);
        
        SimpleMailMessage m = new SimpleMailMessage();

        m.setFrom(from);
        m.setTo(rcpt);
        m.setSubject(subj);
        m.setText(msg);
        sender.send(m);
        log.info("Message sent....");
    }
}
