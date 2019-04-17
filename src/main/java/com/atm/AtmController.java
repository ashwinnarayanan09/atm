package com.atm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atm")
public class AtmController {

    AtmService atmService;

    @Autowired
    public AtmController(AtmService atmService){
        this.atmService = atmService;
    }

    @GetMapping(path = "/{id}/balance")
    public ResponseEntity<Integer> getBalance(@PathVariable("id") Integer id) {

        Integer balance = atmService.getBalance(id);
        return ResponseEntity.ok(balance);
    }

    @PutMapping(path = "/{id}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable("id") Integer id,@RequestParam("amount") Integer amount) {

        atmService.deposit(id,amount);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{id}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable("id") Integer id,@RequestParam("amount") Integer amount) {

        String denomination = atmService.withdraw(id,amount);
        return ResponseEntity.ok(denomination);
    }
}
