package org.example.config;

import org.example.model.Role;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class DbInit {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void postConstruct() {
        User admin = new User();
        admin.setId(0L);
        admin.setFirstName("Admin");
        admin.setSurname("Admin");
        admin.setEmail("admin@HSE.com");
        admin.setAddress("admin");
        admin.setDOB("1999-03-08");
        admin.setNationality("Malaysian");
        admin.setPhone("123456789");
        admin.setPPS("1234567AB");
        admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
        admin.setRoles(Arrays.asList(new Role("ADMIN")));

        userRepository.save(admin);
    }
}
