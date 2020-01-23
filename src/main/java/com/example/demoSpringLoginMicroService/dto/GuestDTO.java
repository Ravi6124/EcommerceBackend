package com.example.demoSpringLoginMicroService.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
public class GuestDTO {

    private String guestId;
    private String time;
    private String type;
}
