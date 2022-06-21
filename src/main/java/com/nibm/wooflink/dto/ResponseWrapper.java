package com.nibm.wooflink.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseWrapper {
    private Object body;
    private String status;
    private String message;
}
