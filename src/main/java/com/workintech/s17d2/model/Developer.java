package com.workintech.s17d2.model;

import org.springframework.stereotype.Component;

@Component
public class Developer {
        private int id;
        private String name;
        private double salary;
        private Experience experience;

        public Developer(){

        }

        public Developer(int id, String name, double salary, Experience experience) {
                this.id = id;
                this.name = name;
                this.salary = salary;
                this.experience = experience;
        }

        public int getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public double getSalary() {
                return salary;
        }

        public Experience getExperience() {
                return experience;
        }

        public void setId(int id) {
                this.id = id;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setSalary(double salary) {
                this.salary = salary;
        }

        public void setExperience(Experience experience) {
                this.experience = experience;
        }

        @Override
        public String toString() {
                return "Developer{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", salary=" + salary +
                        ", experience=" + experience +
                        '}';
        }
}
