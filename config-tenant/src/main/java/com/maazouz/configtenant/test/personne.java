package com.maazouz.configtenant.test;

import lombok.*;

import javax.persistence.*;

@Entity @AllArgsConstructor @NoArgsConstructor
@ToString @Builder @Data
public class personne {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


}
