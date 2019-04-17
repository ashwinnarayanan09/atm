import { Component, OnInit } from '@angular/core';
import {AtmService} from "../../services/atm.service";

@Component({
  selector: 'app-home',
  templateUrl:'./home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  balance:number;
  amount:number;
  message:string;
  denominations:string;

  constructor(private atmService:AtmService) {
    this.balance = 0;
    this.amount = 0;
  }

  ngOnInit() {

    this.getBalance();
  }

  public getBalance(){

    this.atmService.getBalance().subscribe(

      response => {
        this.balance = response;
        console.log(this.balance);
      },
      error => {
       console.log(error);
      }
    );

  }

  public deposit(){

    this.atmService.deposit(this.amount).subscribe(

      response => {
        this.message = "Amount deposited successfully in your account.";
        this.getBalance();
        console.log(response);
      },
      error => {
        console.log(error);
      }
    );


  }

  public withdraw(){

    console.log("amount:"+this.amount);
    this.atmService.withdraw(this.amount).subscribe(

      response => {

        this.getBalance();
        console.log(response);
        this.denominations = response.toString();
        this.message = "Collect cash." + this.denominations;
      },
      error => {
        console.log(error);
        this.message = "Not enough balance in your account.";
      }
    );


  }

  public disableDeposit():Boolean{

    const remainder = this.amount%100;

    if(this.amount == 0 || this.amount < 0 || this.amount/100 < 0 || remainder > 0 || this.amount == null){
      return true;
    }
    else
      return false;


  }

  public disableWithdraw():Boolean{

    const remainder = this.amount%100;

    if(this.amount == 0 || this.amount < 0 || this.amount/100 < 0 || remainder > 0 || this.amount > this.balance || this.amount == null){
      return true;
    }
    else
      return false;


  }

}
