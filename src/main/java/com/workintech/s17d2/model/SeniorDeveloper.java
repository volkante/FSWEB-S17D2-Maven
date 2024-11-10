package com.workintech.s17d2.model;

import org.springframework.stereotype.Component;

@Component
public class SeniorDeveloper extends Developer{

    public SeniorDeveloper() {
    }

    public SeniorDeveloper(int id, String name, double salary) {
        super(id, name, salary, Experience.SENIOR);
    }
}
