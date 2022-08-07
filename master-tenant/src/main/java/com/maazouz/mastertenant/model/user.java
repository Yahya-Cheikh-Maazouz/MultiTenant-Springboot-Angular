package com.maazouz.mastertenant.model;

import lombok.*;

import javax.persistence.*;

@Entity @Data @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
public class user{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username ;
    private String password;
    @OneToOne
    private Tenant tenant;

}
