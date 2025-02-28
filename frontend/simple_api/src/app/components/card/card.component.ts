import { ChangeDetectorRef, Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Cat } from '../../models_and_services/models/catDTO.model';
import { ApiServiceService } from '../../models_and_services/services/api/api-service.service';

import { ToastrServiceService } from '../../models_and_services/services/toastr/toastr-service.service';
import { SingleAttrDTO } from '../../models_and_services/models/singleAttrDTO.model copy';

import { Router } from '@angular/router';
import { SharedCatService } from '../../models_and_services/services/shared-cat.service';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent {
  @Input() cat!: Cat;  // ✅ Accept cat object as input

  constructor(
    private catSer: ApiServiceService,
    private cdr: ChangeDetectorRef,
    private toastrSer: ToastrServiceService,
    private router: Router,
    private sharedCat: SharedCatService
  ){}


  siamese: string = "assets/images/cats/siamese.jpg";
  abyssinian: string = "assets/images/cats/abyssinian.jpg";
  bengal_cat: string = "assets/images/cats/bengal_cat.jpg";
  british_short_hair: string = "assets/images/cats/british_short_hair.jfif";
  maine_coon: string = "assets/images/cats/maine_coon.jpg";

  norwegian_forest: string = "assets/images/cats/norwegian_forest.jpg";
  persian: string = "assets/images/cats/persian.jfif";
  ragdoll_cat: string = "assets/images/cats/ragdoll_cat.jpg";
  scottish_fold: string = "assets/images/cats/scottish_fold.jpg";
  syphnx: string = "assets/images/cats/syphnx.jpg";

  selected_img(cat:Cat){
    if(cat.id == 1){
      return this.siamese;
    }else if(cat.id == 2){
      return this.persian;
    }else if(cat.id == 3){
      return this.maine_coon;
    }else if(cat.id == 4){
      return this.bengal_cat;
    }else if(cat.id == 5){
      return this.ragdoll_cat;
    }else if(cat.id == 6){
      return this.british_short_hair;
    }else if(cat.id == 7){
      return this.syphnx;
    }else if(cat.id == 8){
      return this.scottish_fold;
    }else if(cat.id == 9){
      return this.norwegian_forest;
    }else if(cat.id == 10){
      return this.abyssinian;
    }
  }

  modify(cat:Cat){
    let route:string = "/modify";
    this.sharedCat.setCat(cat);
    this.router.navigate([route]);
  }


  delete(cat:Cat) { 
    let data: SingleAttrDTO = {
      attr0: (cat.id).toString()
    }
    this.catSer.deleteData(data).subscribe( 
      (response) => { 
        console.log('POST Success:', response); 
        this.toastrSer.showSuccess("Succesfull Operation"); 
        window.location.reload();  // 🔄 Reload the page
      }, 
      (error) => { 
        console.error('POST Error:', error); 
        this.toastrSer.showError("Error Occured!"); 
      } 
    ); 
  } 
}


