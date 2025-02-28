import { Component } from '@angular/core';
import { SharedCatService } from '../../models_and_services/services/shared-cat.service';
import { Cat } from '../../models_and_services/models/catDTO.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiServiceService } from '../../models_and_services/services/api/api-service.service';
import { ToastrServiceService } from '../../models_and_services/services/toastr/toastr-service.service';
@Component({
  selector: 'app-modify-page',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './modify-page.component.html',
  styleUrl: './modify-page.component.css'
})
export class ModifyPageComponent {

  constructor(
    private catService: SharedCatService,
    private apiser: ApiServiceService,
    private toastrser: ToastrServiceService
  ) {}

  cat: Cat | null = null; // Fixed nullability issue
  selected_img:string | null = null;

  ngOnInit() {
    this.cat = this.catService.getCat();
    this.selected_img = this.catService.selected_img(this.cat);
    console.log(this.cat);
  }

  new_name:string="";
  new_weight:number=0;

  changeName(){
    let new_cat = new Cat();
    new_cat.id = this.cat.id;
    new_cat.name = this.new_name;
    new_cat.weight = this.cat.weight;

    this.apiser.putData(new_cat).subscribe({
      next : (response)=> {
        console.log("Put Successfull: " + response)
        this.cat.name = this.new_name;
        this.toastrser.showSuccess("Successfull Operation: name changed!");
      },
      error:(error)=>{
        console.error("Update failed:", error);
        if (error.status === 404) {
          this.toastrser.showWarning("Cat not found! Update failed.");
        } else if (error.status === 500) {
          this.toastrser.showError("Internal server error. Please try again later.");
        } else {
          this.toastrser.showError("Failed to update cat name.");
        }
      }
    });
  }

  changeWeight(){
    let new_cat = new Cat();
    new_cat.id = this.cat.id;
    new_cat.name = this.cat.name;
    new_cat.weight = this.new_weight;

    if(this.new_weight!=null && this.new_weight>0){
      this.apiser.putData(new_cat).subscribe({
        next : (response)=> {
          console.log("Put Successfull: " + response)
          this.cat.weight = this.new_weight;
          this.toastrser.showSuccess("Successfull Operation: name changed!");
        },
        error:(error)=>{
          console.error("Update failed:", error);
          if (error.status === 404) {
            this.toastrser.showWarning("Cat not found! Update failed.");
          } else if (error.status === 500) {
            this.toastrser.showError("Internal server error. Please try again later.");
          } else {
            this.toastrser.showError("Failed to update cat name.");
          }
        }
      });
    }else{
      this.toastrser.showWarning("Weight can not be empty nor can be equals and less than zero !");
    }
  }


}
