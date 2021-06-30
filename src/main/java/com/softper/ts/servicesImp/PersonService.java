package com.softper.ts.servicesImp;

import com.softper.ts.models.Person;
import com.softper.ts.models.User;
import com.softper.ts.repositories.IPersonRepository;
import com.softper.ts.repositories.IUserRepository;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.outputs.PersonOutput;
import com.softper.ts.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements IPersonService {

    @Autowired
    IPersonRepository personRepository;
    @Autowired
    IUserRepository userRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deleteById(Integer id) {
        personRepository.deleteById(id);
    }

    @Override
    public Optional<Person> findById(Integer id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }


    @Override
    public BaseResponse findPeopleById(int id) {
        BaseResponse response = new BaseResponse();
        try
        {
            User getUser = userRepository.findUserByPersonId(id).get();
            Person getPerson = personRepository.findById(id).get();

            PersonOutput newPersonOutput = new PersonOutput();
            newPersonOutput.setEmail(getUser.getEmail());
            newPersonOutput.setFirstName(getPerson.getFirstName());
            newPersonOutput.setLastName(getPerson.getLastName());
            if(getPerson.getPersonType()==1)
                newPersonOutput.setUserType("Customer");
            if(getPerson.getPersonType()==2)
                newPersonOutput.setUserType("Driver");
            
            response = new BaseResponse("findPeopleById","success",1);
            response.setPersonOutput(newPersonOutput);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findPeopleById", "An error ocurred while getting the person: "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findAllPersons() {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Person> personList = personRepository.findAll();
            List<PersonOutput> personOutputList = new ArrayList<>();
            for (Person p:personList) {
                Optional<User> getUser = userRepository.findUserByPersonId(p.getId());
                PersonOutput newPersonOutput = new PersonOutput();
                newPersonOutput.setEmail(getUser.get().getEmail());
                newPersonOutput.setFirstName(p.getFirstName());
                newPersonOutput.setLastName(p.getLastName());
                if(p.getPersonType()==1)
                    newPersonOutput.setUserType("Customer");
                if(p.getPersonType()==2)
                    newPersonOutput.setUserType("Driver");
                personOutputList.add(newPersonOutput);
            }
            response = new BaseResponse("findAllPersons","success",1);
            response.setPersonOutputList(personOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllPersons", "An error ocurred while getting the person: "+e.getMessage(),-2);
        }

    }
}
