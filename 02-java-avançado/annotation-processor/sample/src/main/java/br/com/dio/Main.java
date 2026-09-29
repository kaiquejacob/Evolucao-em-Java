package br.com.dio;

import br.com.dio.model.Person;
import br.com.dio.model.PersonBuilder;

public class Main {

    static void main(String[] args) {
        var person = new PersonBuilder().name("Sandro").age(56).build();

        System.out.println(person);

    }
}
