package com.maazouz.configtenant.MultiTenant.Config.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Table(name = "tenantdatasourceconfig")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor @ToString
@NoArgsConstructor @Data
public class DataSource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "driver_class_name")
    private String driverClassName;
    private String name;
    private String url;
    private String username;
    private String password;
    private boolean initialize;
    
}
