package com.maazouz.mastertenant.service.impl;


import com.maazouz.mastertenant.dto.MapperToUser;
import com.maazouz.mastertenant.model.user;
import com.maazouz.mastertenant.repo.tenantRepository;
import com.maazouz.mastertenant.repo.userRepository;
import com.maazouz.mastertenant.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final userRepository userRepository;
    private final tenantRepository tenantRepository;


    @Override
    public ResponseEntity<?> createUser(MapperToUser userR) {
          MapperToUser m = new MapperToUser();
        user user = m.userRequesToUser(userR);
        tenantRepository.save(user.getTenant());
       return ResponseEntity.ok( this.userRepository.save(user)) ;
    }
}
