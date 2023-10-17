package drako.inventory.service;

import drako.inventory.model.Product;

import java.util.List;

public interface IProductService {

    public List<Product> listProducts();

    public Product findProductById(Integer idProduct);

    public Product addProduct(Product product);

    public void deleteProductById(Integer idProduct);
}
