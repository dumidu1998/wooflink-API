package com.nibm.wooflink.service;

import com.nibm.wooflink.dto.req.CustomerSignupRequest;
import com.nibm.wooflink.enums.UserType;
import com.nibm.wooflink.model.Customer;
import com.nibm.wooflink.model.User;
import com.nibm.wooflink.repository.CityRepository;
import com.nibm.wooflink.repository.CustomerRepository;
import com.nibm.wooflink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;


@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;


    @Override
    public org.springframework.security.core.userdetails.User loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        //returning user details to the web security configurer user details according to the requested details
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public boolean checkIfEmailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public void signup(CustomerSignupRequest customerSignUpRequest) {

        //Construct Objects
        User user = new User();
        Customer customer = new Customer();

        //encode password with bcrypt password
        user.setPassword(bcryptPasswordEncoder.encode(customerSignUpRequest.getPassword()));
        user.setEmail(customerSignUpRequest.getEmail());
        user.setContactNo(customerSignUpRequest.getContactNo());
        user.setStreet(customerSignUpRequest.getStreet());
        user.setCity(cityRepository.findById(customerSignUpRequest.getCity()).orElseThrow(RuntimeException::new));
        user.setUserType(UserType.CUSTOMER);
        user.setStreet(customerSignUpRequest.getStreet());

        //save user login data and customer data
        User savedUser = userRepository.save(user);
        customer.setUser(savedUser);
        customerRepository.save(customer);
    }
}
