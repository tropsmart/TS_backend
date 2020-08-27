package com.softper.ts.resources.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerOutput {
    public int id;
    public String firstName;
    public String lastName;
    public double credits;

    public CustomerOutput(int id, String firstName, String lastName, double credits) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.credits = credits;
    }
}
