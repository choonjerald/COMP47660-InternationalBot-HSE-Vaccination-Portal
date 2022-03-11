package org.example.config;

import org.example.model.Question;
import org.example.model.Role;
import org.example.model.User;
import org.example.repository.QuestionRepository;
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

    @Autowired
    private QuestionRepository questionRepository;

    @PostConstruct
    public void postConstruct() {
        User admin = new User();
        admin.setId(1L);
        admin.setFirstName("Admin");
        admin.setSurname("Admin");
        admin.setEmail("admin@HSE.com");
        admin.setSex("male");
        admin.setAddress("admin");
        admin.setDOB("1999-03-08");
        admin.setNationality("Malaysian");
        admin.setPhone("123456789");
        admin.setPPS("1234567AB");
        admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
        admin.setRoles(Arrays.asList(new Role("ADMIN")));

        Question question = new Question();
        question.setTitle("Side effects");
        question.setDetails("If I get either one of the vaccine, what side effects will there be?If I get either one of the vaccine, what side effects will there be?If I get either one of the vaccine, what side effects will there be?");
        question.setId(0L);

        User user1 = new User();
        user1.setId(2L);
        user1.setFirstName("1");
        user1.setSurname("1");
        user1.setEmail("1@HSE.com");
        user1.setSex("male");
        user1.setAddress("user1");
        user1.setDOB("1999-03-08");
        user1.setNationality("Malaysian");
        user1.setPhone("323456789");
        user1.setPPS("2234567AB");
        user1.setPassword(new BCryptPasswordEncoder().encode("p"));
        user1.setRoles(Arrays.asList(new Role("USER")));

        User user2 = new User();
        user2.setId(3L);
        user2.setFirstName("2");
        user2.setSurname("2");
        user2.setEmail("2@HSE.com");
        user2.setSex("male");
        user2.setAddress("user2");
        user2.setDOB("1999-03-08");
        user2.setNationality("Malaysian");
        user2.setPhone("323456789");
        user2.setPPS("3234567AB");
        user2.setPassword(new BCryptPasswordEncoder().encode("p"));
        user2.setRoles(Arrays.asList(new Role("USER")));

        questionRepository.save(question);
        userRepository.save(admin);
        userRepository.save(user1);
        userRepository.save(user2);
    }
}
