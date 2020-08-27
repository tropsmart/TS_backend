package com.softper.ts.resources.comunications;

import com.softper.ts.resources.outputs.PersonOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PersonResponse extends BaseResponse<PersonOutput>{
    public PersonResponse(List<PersonOutput> personResponseList) {super(personResponseList);}
    public PersonResponse(PersonOutput personOutput) { super(personOutput);}
    public PersonResponse(String message) {super(message);}
}
