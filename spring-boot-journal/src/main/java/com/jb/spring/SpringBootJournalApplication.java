package com.jb.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jb.spring.domain.Journal;
import com.jb.spring.repository.JournalRepository;

@SpringBootApplication
public class SpringBootJournalApplication {
	
	@Bean
	InitializingBean saveData(JournalRepository repo) {
		return () -> {
			repo.save(new Journal("스프링 부트 입문", "오늘부터 스프링 부트 공부하기", "07/18/2018"));
			repo.save(new Journal("간단한 스프링 부트 프로젝트", "스프링 부트 프로젝트 최초 생성", "07/19/2018"));
			repo.save(new Journal("스프링 부트 해부", "스프링 부트 상세 보기", "07/20/2018"));
			repo.save(new Journal("스프링 부트 클라우드", "클라우드 파운드리를 응용한 스프링 부트 공부", "07/21/2018"));
		};
	}

	public static void main(String[] args) {
		
//		System.setProperty("http.proxyHost", "70.10.15.10");
//		System.setProperty("http.proxyPort", "8080");
//		System.setProperty("https.proxyHost", "70.10.15.10");
//		System.setProperty("https.proxyPort", "8080");
		
		SpringApplication app = new SpringApplication(SpringBootJournalApplication.class);
		app.run(args);
	}
}
