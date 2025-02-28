import { Component } from '@angular/core';
import { CardComponent } from "../card/card.component";
import { CommonModule } from '@angular/common';  // ✅ Import this!
import { ChangeDetectorRef } from '@angular/core';
import { ApiServiceService } from '../../models_and_services/services/api/api-service.service';
import { Cat } from '../../models_and_services/models/catDTO.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-jpa-controller',
  standalone: true,
  imports: [CardComponent, CommonModule],  // ✅ Add CommonModule here
  templateUrl: './jpa-controller.component.html',
  styleUrl: './jpa-controller.component.css'
})
export class JpaControllerComponent {

  constructor(
    private catSer: ApiServiceService,
    private cdr: ChangeDetectorRef,
    private router: Router
  ){}

  cat_list:Cat[] = [];

  ngOnInit() {
    this.getAllCats();
  }

  getAllCats() {
    this.catSer.getAllCats().subscribe(
      (response) => {
        this.cat_list = response.map((data: any) => {   // leave the data as type any because forcing the received data into fixed structure can cause problems
          const item = new Cat();
          item.id = data.id;
          item.name = data.name;
          item.weight = data.weight;
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
