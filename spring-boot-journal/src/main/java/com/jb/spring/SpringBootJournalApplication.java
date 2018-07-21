package com.jb.spring;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.jb.spring.domain.entity.Journal;
import com.jb.spring.repository.JournalRepository;

@SpringBootApplication
public class SpringBootJournalApplication implements CommandLineRunner, ApplicationRunner{

	private static final Logger log = LoggerFactory.getLogger(SpringBootJournalApplication.class);

	@Bean
	InitializingBean saveData(JournalRepository repo) {
		return () -> {
			repo.save(new Journal("스프링 부트 입문", "오늘부터 스프링 부트 공부하기", "07/18/2018"));
			repo.save(new Journal("간단한 스프링 부트 프로젝트", "스프링 부트 프로젝트 최초 생성", "07/19/2018"));
			repo.save(new Journal("스프링 부트 해부", "스프링 부트 상세 보기", "07/20/2018"));
			repo.save(new Journal("스프링 부트 클라우드", "클라우드 파운드리를 응용한 스프링 부트 공부", "07/21/2018"));
		};
	}

	public static void main(String[] args) throws IOException {

//		System.setProperty("http.proxyHost", "70.10.15.10");
//		System.setProperty("http.proxyPort", "8080");
//		System.setProperty("https.proxyHost", "70.10.15.10");
//		System.setProperty("https.proxyPort", "8080");

		SpringApplication app = new SpringApplication(SpringBootJournalApplication.class);
		app.run(args);
	}

	@Bean
	String info() {
		return "그냥 간단한 문자열 빈입니다.";
	}

	@Autowired
	String info;

	@Override
	// ApplictionRunner : CommandLineRunner 보다 상세한 설정 가능
	// Boot App 시동 전에 실행할 코드 정의
	public void run(ApplicationArguments args) throws Exception {
		log.info("## > ApplicationRunner 구현체...");
		log.info("info 빈에 액세스: "+ info);
		args.getNonOptionArgs().forEach(file -> log.info(file));
	}

	@Override
	// CommandLineRunner
	public void run(String... args) throws Exception {
		log.info("## > CommandLineRunner 구현체...");
		log.info("info 빈에 액세스: "+info);
		for(String arg:args)
			log.info(arg);
		
	}
	
	
}

@Component
class MyComponent {
	private static final Logger log = LoggerFactory.getLogger(MyComponent.class);

	@Autowired
	public MyComponent(ApplicationArguments args) {
		boolean enable = args.containsOption("enable");
		if (enable)
			log.info("## > enalbe 옵션을 주었군요!");

		List<String> _args = args.getNonOptionArgs();
		log.info("## > 다른 인자 ...");
		if (!_args.isEmpty())
			_args.forEach(file -> log.info(file));
	}
}