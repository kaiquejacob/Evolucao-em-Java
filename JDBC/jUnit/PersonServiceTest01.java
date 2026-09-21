package jUnit;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PersonServiceTest01 {
    static void main(String[] args) {
        Person person = new Person(15);
        PersonService personService = new PersonService();
        log.info("Is Adult? '{}'", personService.isAdult(person));

    }
}
