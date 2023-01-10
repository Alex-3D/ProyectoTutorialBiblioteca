package com.alex3d.biblioteca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class BibliotecaApplication {
	private final static Logger log = LoggerFactory.getLogger(BibliotecaApplication.class);

	public static void main(String[] args) {
		
		log.info("Spring version : {}", SpringVersion.getVersion());
		SpringApplication.run(BibliotecaApplication.class, args);
	}

}
