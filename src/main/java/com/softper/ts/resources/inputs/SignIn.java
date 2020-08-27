package com.softper.ts.resources.inputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignIn {
    private String email;
    private String password;

}
