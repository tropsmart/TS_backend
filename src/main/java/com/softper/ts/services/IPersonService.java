package com.softper.ts.services;

import com.softper.ts.models.Person;
import com.softper.ts.resources.comunications.PersonResponse;

public interface IPersonService extends ICrudService<Person> {
    PersonResponse findPeopleById(int id);
    PersonResponse findAllPersons();
}
