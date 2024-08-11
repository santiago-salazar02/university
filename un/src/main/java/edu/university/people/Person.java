package edu.university.people;

import edu.university.helpers.Format;

public class Person {
    private String name;

    public Person(String name){
        setName(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = Format.formatName(name);
    }
}
