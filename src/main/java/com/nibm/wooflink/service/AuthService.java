package com.nibm.wooflink.service;

import com.nibm.wooflink.dto.req.CustomerSignupRequest;
import com.nibm.wooflink.dto.req.SignInRequest;
import com.nibm.wooflink.dto.res.SignInResponse;
import com.nibm.wooflink.enums.UserType;
import com.nibm.wooflink.model.Customer;
import com.nibm.wooflink.model.User;
import com.nibm.wooflink.repository.CityRepository;
import com.nibm.wooflink.repository.CustomerRepository;
import com.nibm.wooflink.repository.UserRepository;
import com.nibm.wooflink.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    public boolean checkIfEmailExists(String email) {
        return userRepository.findByEmail(email) != null; //true if exists
    }

    public boolean signup(CustomerSignupRequest customerSignUpRequest) {
        try {
            //Construct Objects
            User user = new User();
            Customer customer = new Customer();

            //encode password with bcrypt password
            user.setFirstName(customerSignUpRequest.getFirstName());
            user.setLastName(customerSignUpRequest.getLastName());
            user.setEmail(customerSignUpRequest.getEmail());
            user.setContactNo(customerSignUpRequest.getContactNo());
            user.setPassword(bcryptPasswordEncoder.encode(customerSignUpRequest.getPassword()));
            user.setCity(cityRepository.findById(customerSignUpRequest.getCity()).orElseThrow(RuntimeException::new));
            user.setUserType(UserType.CUSTOMER);
            user.setStreet(customerSignUpRequest.getStreet());

            //save user login data and customer data
            User savedUser = userRepository.save(user);
            customer.setUser(savedUser);
            customerRepository.save(customer);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public SignInResponse login(SignInRequest signInRequest) {
        User user = this.userRepository.findByEmail(signInRequest.getEmail());
        //check password and with the user email with authentication manager
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), signInRequest.getPassword())
            );
        } catch (Exception ex) {
            //throw error if emails and password does not match
            throw new RuntimeException("Invalid Password");
        }
        //get jwt token
        String token = jwtTokenUtil.generateToken(user.getEmail());

        SignInResponse response = new SignInResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setUserType(user.getUserType());
        response.setToken(token); //append to response entity
        return response;
    }

    @Override
    public org.springframework.security.core.userdetails.User loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        //returning user details to the web security configurer user details according to the requested details
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public boolean checkIfContactNumberExists(String contactNo) {
        return userRepository.findByContactNo(contactNo) != null; //true if exists
    }
}
