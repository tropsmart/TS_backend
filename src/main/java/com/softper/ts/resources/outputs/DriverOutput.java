package com.softper.ts.resources.outputs;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverOutput {
    private int id;
    private String firstName;
    private String lastName;
    private String license;
    private String email;
    private int role;
    private int roleId;
    private List<String> vehicles;
    private List<String> locations;

    public DriverOutput(int id, String firstName, String lastName, String license, String email, int role, int roleId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.license = license;
        this.email = email;
        this.role = role;
        this.roleId = roleId;
    }
}
