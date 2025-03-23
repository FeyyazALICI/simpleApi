import { Component } from '@angular/core';
import { CardComponent } from "../card/card.component";
import { CommonModule } from '@angular/common';  // ✅ Import this!
import { ChangeDetectorRef } from '@angular/core';
import { ApiServiceService } from '../../models_and_services/services/api/api-service.service';
import { Cat } from '../../models_and_services/models/catDTO.model';
import { Router } from '@angular/router';
import { CatWithPriceDTO } from '../../models_and_services/models/catWithPriceDTO';
import { CardfullComponent } from "../cardfull/cardfull.component";

@Component({
  selector: 'app-viewcontroller',
  standalone: true,
  imports: [CardComponent, CommonModule, CardfullComponent],  // ✅ Add CommonModule here
  templateUrl: './viewcontroller.component.html',
  styleUrl: './viewcontroller.component.css'
})
export class ViewcontrollerComponent {
  
  constructor(
    private catSer: ApiServiceService,
    private cdr: ChangeDetectorRef,
    private router: Router
  ){}

  cat_list:CatWithPriceDTO[] = [];

  ngOnInit() {
    this.getViewJpa();
  }

  getViewJpa() {
    this.catSer.getViewJpa().subscribe(
      (response) => {
        this.cat_list = response.map((data: any) => {   // leave the data as type any because forcing the received data into fixed structure can cause problems
          const item = new CatWithPriceDTO();
          item.id = data.id;
          item.name = data.name;
          item.weight = data.weight;
          item.price = data.price;
          return item;
        });
        this.cdr.detectChanges();
        // console.log('Success:', response);
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }

  inserCat(){
    this.router.navigate(["/insert-cat"]);
  }
}
