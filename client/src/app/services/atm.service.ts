import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class AtmService {

  private baseUrl = 'api/atm';

  constructor(private http: HttpClient) {
  }

  getBalance() {

    const url = this.baseUrl + "/1/balance";
    return this.http.get<number>(url,{responseType: 'number' as 'json'});
  }

  deposit(amount:number) {

    const url = this.baseUrl + "/1/deposit?amount="+amount;
    return this.http.put(url,{});
  }

  withdraw(amount:number) {

    const url = this.baseUrl + "/1/withdraw?amount="+amount;
    return this.http.put(url,{},{responseType: 'text' as 'json'});
  }
}
