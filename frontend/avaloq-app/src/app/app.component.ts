import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Http, Response, Headers, RequestOptions } from "@angular/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Dice Game';
  diceRolled;
  angForm: FormGroup;
  constructor(private fb: FormBuilder,
    private http: Http) {
    this.createForm();
  }

  createForm() {
    this.angForm = this.fb.group({
      diceCount: ['', Validators.required],
      diceFaces: ['', Validators.required],
      diceRollCount: ['', Validators.required]
    });
  }

  onSubmit() {
    const result = Object.assign({}, this.angForm.value);
    this.rollDice(result);
  }

  rollDice(diceRequest) {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    let body = JSON.stringify(diceRequest);
    return this.http.post('http://localhost:8080/dice/rollDice', body, { headers })
      //.map((res: Response) => res.json())
      .subscribe(
        (res: Response) => {
          this.diceRolled = res.json().sumTotal;
          console.log(this.diceRolled)
        },
        (err) => {
          console.log("ERROR: " + err);
        },
        () => {
          console.log("COMPLETED");
        }
      )
  }

  parseRolledDice(dice) {
    if(dice){
      return  JSON.stringify(dice);
    }
    
  }

}