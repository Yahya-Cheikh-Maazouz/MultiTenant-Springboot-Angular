package com.maazouz.mastertenant.dto;



import com.maazouz.mastertenant.model.Tenant;
import com.maazouz.mastertenant.model.user;
import com.maazouz.mastertenant.secutity.securityAESEncoder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;


@Component
@Data @RequiredArgsConstructor
public class MapperToUser {
   private String username ;
   private String password;
   @Autowired
   private Environment env;
   private static Environment environment;

   @PostConstruct
   public void init(){
      environment = env;
   }

   public user userRequesToUser(MapperToUser userRequest){
      String keyEncoder = environment.getProperty("encoder.key");

      user userReturned =  user.builder()
              .username(userRequest.getUsername())
              .password(userRequest.getPassword())
              .build();

      System.out.println(securityAESEncoder.encrypt("com.mysql.cj.jdbc.Driver",keyEncoder));
      Tenant t = Tenant.builder()
              .name(UUID.randomUUID().toString())
              .username(securityAESEncoder.encrypt("root",keyEncoder))
              .password(securityAESEncoder.encrypt("root",keyEncoder))
              .driverClassName(securityAESEncoder.encrypt("com.mysql.cj.jdbc.Driver",keyEncoder))
              .initialize(false)
              .url(securityAESEncoder.encrypt("jdbc:mysql://localhost:8889",keyEncoder))
              .build();
      userReturned.setTenant(t);

      return userReturned;

   }


}
