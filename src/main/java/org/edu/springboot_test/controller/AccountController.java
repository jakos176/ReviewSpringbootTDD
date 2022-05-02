package org.edu.springboot_test.controller;

import org.edu.springboot_test.models.Account;
import org.edu.springboot_test.models.TransactionDTO;
import org.edu.springboot_test.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountServices accountServices;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account detail(@PathVariable Long id) {
        return accountServices.findById(id);
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> transfer(@RequestBody TransactionDTO transactionDTO) {
        accountServices.transfer(transactionDTO.getOriginAccountDTO(), transactionDTO.getDestinyAccountDTO(),
                transactionDTO.getAmount(), transactionDTO.getBankId());

        Map<String, Object> response = new HashMap<>();
        response.put("date", LocalDate.now().toString());
        response.put("status", "OK");
        response.put("message", "transfer success!");
        response.put("transaction", transactionDTO);

        return ResponseEntity.ok(response);

    }


}
