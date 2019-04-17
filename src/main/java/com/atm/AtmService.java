package com.atm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtmService {

    @Autowired
    AtmRepository atmRepository;


    public Integer getBalance(Integer id){

        return atmRepository.getbalance(id);
    }

    public void deposit(Integer id,Integer amount){

        atmRepository.deposit(id,amount);
    }

    public String withdraw(Integer id,Integer amount){

       return atmRepository.withdraw(id,amount);

    }
}
