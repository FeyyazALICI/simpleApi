import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { JpaControllerComponent } from './components/jpa-controller/jpa-controller.component';
import { NativeControllerComponent } from './components/native-controller/native-controller.component';
import { ModifyPageComponent } from './components/modify-page/modify-page.component';
import { InsertCatComponent } from './components/insert-cat/insert-cat.component';


export const routes: Routes = [
    { path: '', component: HomeComponent},
    { path: 'jpa', component: JpaControllerComponent},
    { path: 'native', component: NativeControllerComponent},
    { path: 'modify', component: ModifyPageComponent},
    { path: 'insert-cat', component: InsertCatComponent}
];


