package com.nibm.wooflink.controller;

import com.nibm.wooflink.dto.ResponseWrapper;
import com.nibm.wooflink.dto.req.CustomerSignupRequest;
import com.nibm.wooflink.dto.req.SignInRequest;
import com.nibm.wooflink.dto.res.SignInResponse;
import com.nibm.wooflink.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody CustomerSignupRequest customerSignUpRequest) {
        String email = customerSignUpRequest.getEmail();
        String responseMsg;
        if (authService.checkIfEmailExists(email)) {
            responseMsg = "Email exists";
        } else if(authService.checkIfContactNumberExists(customerSignUpRequest.getContactNo())){
            responseMsg = "Contact Number exists";
        }else {
            if (authService.signup(customerSignUpRequest)) {
                responseMsg = "Customer Added Successfully";
                return ResponseEntity.ok().body(responseMsg);
            } else return ResponseEntity.badRequest().body("Error Occurred! Please Try again!!");
        }
        return ResponseEntity.badRequest().body(responseMsg);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper> login(@RequestBody SignInRequest signInRequest) {
        //get object of relavant user
        String email = signInRequest.getEmail();
        //continue if user exists on provided details
        if (authService.checkIfEmailExists(email)) {
            SignInResponse response = authService.login(signInRequest);
            return ResponseEntity.ok(new ResponseWrapper(response, "success", "Login Successfull!!"));
        } else {
            String responseMsg = "UserName or email Invalid";
            return ResponseEntity.badRequest().body(new ResponseWrapper(null, "Failed", responseMsg));

        }
    }


}
