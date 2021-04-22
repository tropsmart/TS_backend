package com.softper.ts.resources.outputs;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthenticatedOutput {
    
    private Integer id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer role;
    private Integer roleId;
    private String token;
    //private String refreshToken;

    public AuthenticatedOutput(int id, String email, String password, String firstName, String lastName, int role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public AuthenticatedOutput(String email) {
        this.email = email;
    }
}
