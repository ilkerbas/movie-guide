package com.appmovieguide.movieguide;

public class PersonFactory {

    //use getPerson method to get object of type person
    public ICinemaPerson getPerson(String personType, String personName){
        if(personType == null){
            return null;
        }
        if(personType.equalsIgnoreCase("PLAYER")){
            return new Player(personName);
        }
        else if(personType.equalsIgnoreCase("DIRECTOR")){
            return new Director(personName);
        }
        return null;

    }
}
