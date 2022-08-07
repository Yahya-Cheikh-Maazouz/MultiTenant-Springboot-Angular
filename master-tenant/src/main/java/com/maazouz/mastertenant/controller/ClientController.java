package com.maazouz.mastertenant.controller;

import com.maazouz.mastertenant.dto.MapperToUser;
import com.maazouz.mastertenant.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/accounts")
    public ResponseEntity<?> getAccounts(@RequestBody MapperToUser userRequest){
        return ResponseEntity.ok(clientService.createUser(userRequest));
    }

}

