package com.workintech.s17d2.rest;

import com.workintech.s17d2.model.*;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    public Map<Integer, Developer> developers;
    private Taxable taxable;

    @PostConstruct
    public void init(){

        this.developers = new HashMap<>();
    }

    @Autowired
    public DeveloperController(Taxable taxable){
        this.taxable = taxable;
    }

    @GetMapping("")
    public List<Developer> getAllDevelopers(){
        System.out.println(developers.values().stream().toList());
        return developers.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Developer findDeveloperById(@PathVariable int id){
        return developers.get(id);
    }

    @PostMapping("")
    public List<Developer> addDeveloper(@RequestBody Developer developer){
        Developer developerToAdd;
        double taxedSalary = applyTax(developer.getSalary(), developer.getExperience());

         if(developer.getExperience() == Experience.JUNIOR){
             developerToAdd = new JuniorDeveloper(developer.getId(), developer.getName(), taxedSalary);
         } else if(developer.getExperience() == Experience.MID){
             developerToAdd = new MidDeveloper(developer.getId(), developer.getName(), taxedSalary);
         } else{
             developerToAdd = new SeniorDeveloper(developer.getId(), developer.getName(), taxedSalary);
         }
            developers.put(developer.getId(), developerToAdd);
            return new ArrayList<>(developers.values());
    }

    private double applyTax(double salary, Experience experience)
    {
        double taxRate;
        switch(experience){
            case JUNIOR:
                taxRate = taxable.getSimpleTaxRate();
                break;
            case MID:
                taxRate = taxable.getMiddleTaxRate();
                break;
            case SENIOR:
                taxRate = taxable.getUpperTaxRate();
                break;
            default:
                taxRate = 0;
        }
        return salary - (salary * taxRate/100);

    }

    @PutMapping("/{id}")
    public void updateDeveloper(@PathVariable int id, @RequestBody Developer developer){
        if (!developers.containsKey(id)) {
            throw new NoSuchElementException("Developer with id " + id + " not found");
        }

        developer.setId(id); // Güncellenen developer'ın id'sini ayarla
        developers.put(id, developer); // Map'e ekle
    }
    /*    @PutMapping("/{id}")
        public Developer updateDeveloper(@PathVariable int id, @RequestBody Developer developer){
            if(!developers.containsKey(id)){
                throw new NoSuchElementException("id not found");
            }

            double taxedSalary = applyTax(developer.getSalary(),developer.getExperience());

            Developer updatedDeveloper;
            if(developer.getExperience() == Experience.JUNIOR){
                updatedDeveloper = new JuniorDeveloper(id, developer.getName(), taxedSalary);
            } else if (developer.getExperience() == Experience.MID){
                updatedDeveloper = new MidDeveloper(id,developer.getName(),taxedSalary);
            } else {
                updatedDeveloper = new SeniorDeveloper(id, developer.getName(), taxedSalary);
            }
            developers.put(id,updatedDeveloper);
            return updatedDeveloper;

        }*/

    @DeleteMapping("/{id}")
    public void removeDeveloper(@PathVariable int id){
        developers.remove(id);
    }





}
