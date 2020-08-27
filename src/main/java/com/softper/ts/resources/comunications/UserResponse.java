package com.softper.ts.resources.comunications;

import com.softper.ts.resources.outputs.UserOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserResponse extends BaseResponse<UserOutput>{
    public UserResponse(List<UserOutput> userOutputList) {super(userOutputList);}
    public UserResponse(UserOutput userOutput) {super(userOutput);}
    public UserResponse(String message) {super(message);}
}
