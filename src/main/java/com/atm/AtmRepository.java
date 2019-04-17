package com.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.beans.IntrospectionException;

@Repository
public class AtmRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer getbalance(Integer id) {

        String sql = "SELECT BALANCE FROM ACCOUNT WHERE ID = ?";

        Integer balance = (Integer)jdbcTemplate.queryForObject(sql, new Object[] { id }, Integer.class);

        return balance;
    }

    public void deposit(Integer id,Integer amount) {

        Integer balance = getbalance(id);
        Integer totalAmount = amount + balance;

        String sql = "UPDATE ACCOUNT  SET BALANCE = ? WHERE ID = ?";

        jdbcTemplate.update(sql,totalAmount,id);
    }

    public String withdraw(Integer id,Integer amount) {

        Integer balance = getbalance(id);
        Integer totalAmount = balance - amount;

        String sql = "UPDATE ACCOUNT  SET BALANCE = ? WHERE ID = ?";

        jdbcTemplate.update(sql,totalAmount,id);


        return calcDispense(amount);
    }

    public String calcDispense(Integer amount){

        String denomination ="";
        Integer highest = 500;
        Integer lowest = 100;
        Integer remainder;
        Integer noLowest ;
        Integer noHighest;
      //  System.out.println("amount = 500"+amount+":"+highest);
        //dispense cash
        if(amount > highest){

            Integer mod;
            Integer div;

            mod = amount%highest;
            div = amount/highest;

            if(mod == 0){
                denomination = amount/highest+ " X " +div;
            }
            else {

                noHighest = div;
                noLowest = mod / lowest;
                denomination = noHighest + " X " + highest + ":" + noLowest + " X " + lowest;
            }
        }

        else if(amount.equals(highest)){
            noHighest = 1;
            denomination = noHighest+ " X " +highest;
        }
        else{
            noLowest = amount/lowest;
            denomination = noLowest + " X " + lowest;
        }


       return denomination;
    }
}
