package com.jb.spring.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jb.spring.domain.Journal;
import com.jb.spring.repository.JournalRepository;

@Controller
public class JournalController {
	
	private static final Logger log = LoggerFactory.getLogger(JournalController.class);
	
	@Autowired
	JournalRepository repo;
	
	@RequestMapping("/")
	public String index(Model model) {
		log.info("::::: request mapping '/' ::::::::");	
		
		model.addAttribute("journal", repo.findAll());		
		log.info(model.toString());
		
		return "index";
	}

	@RequestMapping(value="/journals", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public @ResponseBody List<Journal> getJournal(){
		return repo.findAll();
	}
}
