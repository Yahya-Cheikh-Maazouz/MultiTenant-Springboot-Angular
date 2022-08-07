package com.maazouz.configtenant.test;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class controller {
    private final personneRepo api;


    @PostMapping(path = "/personnes")
    void AddPersonne(@RequestBody personne p){
        this.api.save(p);
    }


    @GetMapping(path = "/personnes")
    List<personne> Personnes(@RequestBody personne p){
       return this.api.findAll();
    }


}
