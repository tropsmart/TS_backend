package com.softper.ts.resources.inputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInput {
    public String FirstName;
    public String LastName;
    public String Email;
    public String Password;
    public String Phone;
    public int Discriminator;
}
