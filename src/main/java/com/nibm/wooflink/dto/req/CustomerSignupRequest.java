package com.nibm.wooflink.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSignupRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private String password;
    private String street;
    private Long city;
}
