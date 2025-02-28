import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastrServiceService } from '../../models_and_services/services/toastr/toastr-service.service';
import { ApiServiceService } from '../../models_and_services/services/api/api-service.service';
import { Cat } from '../../models_and_services/models/catDTO.model';
import { CatStringified } from '../../models_and_services/models/catDTOStringified';

@Component({
  selector: 'app-insert-cat',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './insert-cat.component.html',
  styleUrl: './insert-cat.component.css'
})
export class InsertCatComponent {

  constructor(
    private toastrSer: ToastrServiceService,
    private apiSer:ApiServiceService
  ){

  }

  name:string="";
  weight:number=0;

  insertCat(){
    if(this.name==null || this.name==""){
      this.toastrSer.showError("Name can not be left empty !");
    }
    if(this.weight==null || this.weight<=0){
      this.toastrSer.showError("Weight can not be left empty nor it can be equals to or less than zero !");
    }
    else{
      let cat = new CatStringified();
      cat.name=this.name;
      cat.weight=this.weight.toString();
      console.log(cat);
      this.apiSer.postData(cat).subscribe({
        next : (response)=> {
          console.log("Put Successfull: " + response)
          this.toastrSer.showSuccess("Successfull Operation: name changed!");
        },
        error:(error)=>{
          console.error("Update failed:", error);
          if (error.status === 409) {
            this.toastrSer.showWarning("Cat name is in use!");
          } else if (error.status === 500) {
            this.toastrSer.showError("Internal server error. Please try again later.");
          } else {
            this.toastrSer.showError("Failed to update cat name.");
          }
        }
      });
    }
  }
}
