package com.softper.ts.resources.inputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUp {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int discriminator;


}
