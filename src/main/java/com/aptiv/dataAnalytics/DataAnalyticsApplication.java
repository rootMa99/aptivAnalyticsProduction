package com.aptiv.dataAnalytics;

import com.aptiv.dataAnalytics.domain.Admin;
import com.aptiv.dataAnalytics.domain.Role;
import com.aptiv.dataAnalytics.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DataAnalyticsApplication implements CommandLineRunner {

	@Autowired
	AdminRepo adminRepo;
	public static void main(String[] args) {
		SpringApplication.run(DataAnalyticsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Admin admin=adminRepo.findByRole(Role.ADMIN);
		if (admin==null){
			Admin admini=new Admin();
			admini.setAdminName("admin");
			admini.setPassword(new BCryptPasswordEncoder().encode("admin"));
			admini.setRole(Role.ADMIN);
			adminRepo.save(admini);
		}
	}
}
