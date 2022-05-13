package org.example.service;

import org.example.dto.UserRegistrationDto;
import org.example.exception.EmailNotFoundException;
import org.example.exception.UserAlreadyExistException;
import org.example.model.Role;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    public boolean checkIfUserExist(String email) throws EmailNotFoundException {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) throws UserAlreadyExistException {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getSurname(), registrationDto.getDOB(), registrationDto.getAgeGroup(), registrationDto.getSex(), registrationDto.getPPS(), registrationDto.getAddress(), registrationDto.getPhone(), registrationDto.getEmail(), registrationDto.getNationality(),
                passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("USER")));

        //Let's check if user already registered with us, if not proceed with saving new user
        try {
            if (checkIfUserExist(user.getEmail())) {
                throw new UserAlreadyExistException("User already exists for this email");
            }
        } catch (EmailNotFoundException e) {
            return userRepository.save(user);
        }

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userRepository.findByEmail(email);
        } catch (EmailNotFoundException e) {
            e.printStackTrace();
        }
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}