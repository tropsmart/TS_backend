package com.softper.ts.services;

import com.softper.ts.models.Person;
import com.softper.ts.resources.comunications.BaseResponse;

public interface IPersonService extends ICrudService<Person> {
    BaseResponse findPeopleById(int id);
    BaseResponse findAllPersons();
}
