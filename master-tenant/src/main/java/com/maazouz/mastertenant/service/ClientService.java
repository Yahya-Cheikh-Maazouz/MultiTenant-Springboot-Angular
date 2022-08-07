package com.maazouz.mastertenant.service;

import com.maazouz.mastertenant.dto.MapperToUser;
import org.springframework.http.ResponseEntity;


public interface ClientService {

    ResponseEntity<?> createUser(MapperToUser userRequest);
}
