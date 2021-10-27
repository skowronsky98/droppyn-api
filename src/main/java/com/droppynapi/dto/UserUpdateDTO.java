package com.droppynapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    private String id;
    private String username;
    private String firstname;
    private String surname;
    private String phone;
    private String photoURL;
    private String bio;
    private String idDefultSize;
}
