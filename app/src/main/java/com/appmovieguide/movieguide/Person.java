package com.appmovieguide.movieguide;

public class Person implements IPerson {

    String name;

    public Person(String name){
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
