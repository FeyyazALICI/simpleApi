import { Injectable } from '@angular/core';
import { Cat } from '../models/catDTO.model';

@Injectable({
  providedIn: 'root'
})
export class SharedCatService {

  private cat: Cat | null = null;

  setCat(cat: Cat) {
    this.cat = cat;
  }

  getCat(): Cat | null {
    return this.cat;
  }

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
}