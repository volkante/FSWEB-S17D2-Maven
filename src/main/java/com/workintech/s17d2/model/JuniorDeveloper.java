package com.workintech.s17d2.model;

import org.springframework.stereotype.Component;

@Component
public class JuniorDeveloper extends Developer{

    public JuniorDeveloper() {
    }

    public JuniorDeveloper(int id, String name, double salary) {
        super(id, name, salary, Experience.JUNIOR);
    }


}
