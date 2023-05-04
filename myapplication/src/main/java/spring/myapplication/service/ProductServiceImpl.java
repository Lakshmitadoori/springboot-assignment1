package spring.myapplication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.myapplication.entity.Category;
import spring.myapplication.entity.Product;
import spring.myapplication.repository.CategoryRepository;
import spring.myapplication.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    @Override
    public Product findById(int theId) {
        Optional<Product> result = productRepository.findById(theId);

        Product product = null;

        if (result.isPresent()) {
            product = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find product id - " + theId);
        }

        return product;
    }



    @Override
    public void save (Product product ){

        productRepository.save(product);
    }
    @Override
    public void deleteById(int theId) {
        productRepository.deleteById(theId);
    }

    @Override
    public void saveProductWithCategory(String title, String categoryName) {
        Product product = new Product();
        product.setName(title);

        Category category = categoryRepository.findByCategoryName(categoryName);

        if (category == null) {
            category = new Category();
            category.setCategoryName(categoryName);
            categoryRepository.save(category);
        }

        product.getCategories().add(category);

        productRepository.save(product);
    }
}
