import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Page} from "../model/page";
import {Product} from "../model/product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  public findAll() {
    return this.http.get<Page>('rest/v1/product/all');
  }

  public findById(id: number) {
    return this.http.get<Product>('rest/v1/product/${id}/id');
  }

  public save(product: Product) {
    return this.http.put<Product>('rest/v1/product', product);
  }
}
