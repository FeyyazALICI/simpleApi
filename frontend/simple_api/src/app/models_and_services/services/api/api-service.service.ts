import { Injectable } from '@angular/core';

import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiServiceService {

  constructor(private http: HttpClient) {}
  //JPA -----------------------------------------------------------------------------------------------
  private apiUrl_all_cats = 'http://localhost:9000/jpa';
  //GET 
  getAllCats(): Observable<any> {
    return this.http.get(this.apiUrl_all_cats);
  }

  //DELETE 
  deleteData(data): Observable<any> { 
    console.log('ApiServiceService.deleteData instantiated'); 
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' }); 
   
    const options = { 
      headers: headers, 
      body: data  // Pass data as the request body 
    }; 
   
    return this.http.delete(this.apiUrl_all_cats, options); 
  } 

  // POST
  postData(data: any): Observable<any> {
    console.log('ApiServiceService.postData instantiated');
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.apiUrl_all_cats, data, { headers });
  }

  // PUT (Without ID)
  putData(data: any): Observable<any> {
    console.log('ApiServiceService.putData instantiated');
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put(this.apiUrl_all_cats, data, { headers });
  }
  //JPA -----------------------------------------------------------------------------------------------

  //NATIVE -----------------------------------------------------------------------------------------------
  private apiUrl_all_catsNative = 'http://localhost:9000/native';
  //GET 
  getAllCatsNative(): Observable<any> {
    return this.http.get(this.apiUrl_all_catsNative);
  }
  //NATIVE -----------------------------------------------------------------------------------------------


  //VIEW-JPA -----------------------------------------------------------------------------------------------
  private apiUrl_view_jpa = 'http://localhost:9000/jpa/view';
  //GET 
  getViewJpa(): Observable<any> {
    return this.http.get(this.apiUrl_view_jpa);
  }
  //NATIVE -----------------------------------------------------------------------------------------------
  
}
