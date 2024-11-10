package com.workintech.s17d2.model;

import org.springframework.stereotype.Component;

@Component
public class MidDeveloper extends Developer{

    public MidDeveloper() {
    }

    public MidDeveloper(int id, String name, double salary) {
        super(id, name, salary, Experience.MID);
    }


}
