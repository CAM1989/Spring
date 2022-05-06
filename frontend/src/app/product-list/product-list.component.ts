import { Component, OnInit } from '@angular/core';
import {ProductService} from "../services/product.service";
import {Product} from "../model/product";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];

  constructor(public productService: ProductService) { }

  ngOnInit(): void {
    this.productService.findAll()
      .subscribe(res => {
        this.products = res.content;
      }, error => {
        console.error(error);
      });
  }

  delete(id: number | null) {

  }
}
