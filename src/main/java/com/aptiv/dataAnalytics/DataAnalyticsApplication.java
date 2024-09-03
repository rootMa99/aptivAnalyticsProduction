package com.aptiv.dataAnalytics;

import com.aptiv.dataAnalytics.domain.Admin;
import com.aptiv.dataAnalytics.domain.Coordinator;
import com.aptiv.dataAnalytics.domain.Role;
import com.aptiv.dataAnalytics.repository.AdminRepo;
import com.aptiv.dataAnalytics.repository.CoordinatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DataAnalyticsApplication implements CommandLineRunner {

	@Autowired
	AdminRepo adminRepo;
	@Autowired
	CoordinatorRepo coordinatorRepo;
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
		Admin manager=adminRepo.findByRole(Role.MANAGER);
		if (manager==null){
			Admin admini=new Admin();
			admini.setAdminName("manager");
			admini.setPassword(new BCryptPasswordEncoder().encode("manager"));
			admini.setRole(Role.MANAGER);
			System.out.println(admini);
			adminRepo.save(admini);
		}
		List<Coordinator> coordinators= coordinatorRepo.findAll();
		for (Coordinator c: coordinators){
			if (c.getName() != null){
				Optional<Admin> a=adminRepo.findByAdminName(c.getName());
				System.out.println(a);
				if (a.isEmpty()){
					Admin admini=new Admin();
					admini.setAdminName(c.getName());
					admini.setPassword(new BCryptPasswordEncoder().encode(c.getName()));
					admini.setRole(Role.COORDINATOR);
					System.out.println("coord:"+admini);
					adminRepo.save(admini);
				}
			}
		}
	}
}
