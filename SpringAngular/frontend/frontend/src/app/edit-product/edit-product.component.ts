import { Component } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
})
export class EditProductComponent {
  product: Product = new Product();
  id: number;
  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router
  ) {}
  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.productService.getProductById(this.id).subscribe({
      next: (data) => (this.product = data),
      error: (error: any) => console.log(error),
    });
  }
  onSubmit() {
    this.editProduct();
  }

  editProduct() {
    this.productService.updateProduct(this.id, this.product).subscribe({
      next: (data) => this.toProductList(),
      error: (error) => console.log(error),
    });
  }

  toProductList() {
    this.router.navigate(['/products']);
  }
}
