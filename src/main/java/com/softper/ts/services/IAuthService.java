package com.softper.ts.services;

import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.RefreshInput;
import com.softper.ts.resources.inputs.SignUp;

public interface IAuthService {
    BaseResponse registerComplete(SignUp signUp);
    BaseResponse login(String email, String password);
    BaseResponse loginFixed(String email, String password);
    //BaseResponse refresh(RefreshInput refreshInput) throws Exception;
}
