package com.eda.customerapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String city;
}