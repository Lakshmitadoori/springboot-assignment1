package spring.myapplication.service;


import spring.myapplication.entity.Product;

import java.util.List;


public interface ProductService {
    public List<Product> findAll();

    public Product findById(int theId);

    public void deleteById(int theId);
    public void save(Product product);


    public void saveProductWithCategory(String title, String categoryName);
}
