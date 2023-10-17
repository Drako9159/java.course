import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from './product';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private urlBase = 'http://localhost:8081/inventary-app/products';

  constructor(private httpClient: HttpClient) {}
  getProductsList(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.urlBase);
  }

  addProduct(product: Product): Observable<Object> {
    return this.httpClient.post(this.urlBase, product);
  }

  getProductById(id: number) {
    return this.httpClient.get<Product>(`${this.urlBase}/${id}`);
  }

  updateProduct(id: number, product: Product): Observable<Object> {
    return this.httpClient.put(`${this.urlBase}/${id}`, product);
  }

  deleteProduct(id: number): Observable<object> {
    return this.httpClient.delete(`${this.urlBase}/${id}`);
  }
}
