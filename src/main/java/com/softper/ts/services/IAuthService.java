package com.softper.ts.services;

import com.softper.ts.resources.comunications.AuthResponse;
import com.softper.ts.resources.inputs.RefreshInput;
import com.softper.ts.resources.inputs.SignUp;

public interface IAuthService {
    AuthResponse registerComplete(SignUp signUp);
    AuthResponse login(String email, String password);
    //AuthResponse refresh(RefreshInput refreshInput) throws Exception;
}
