package com.nibm.wooflink.dto.res;

import com.nibm.wooflink.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserType userType;
    private String token;
}
