package spring.myapplication.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import spring.myapplication.entity.Product;
import spring.myapplication.repository.CategoryRepository;
import spring.myapplication.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServicesTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService = new ProductServiceImpl(productRepository);

    @Test
    public void testFindAll() {
        // Create some sample data
        List<Product> products = new ArrayList<Product>();
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());

        // Mock the behavior of the productRepository.findAll() method
        when(productRepository.findAll()).thenReturn(products);

        // Call the findAll() method on the productService
        List<Product> result = productService.findAll();

        // Verify that the result matches the expected value
        Assertions.assertEquals(3, result.size());
    }
    @Test
    public void testFindById() {
        // Create some sample data
        Product product = new Product();
        product.setId(1);
        product.setName("The Great Gatsby");

        // Mock the behavior of the productRepository.findById() method
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // Call the findById() method on the productService
        Product result = productService.findById(1);

        // Verify that the result matches the expected value
        Assertions.assertEquals("The Great Gatsby", result.getName());
    }
    @Test
    public void testSave() {
        // Create some sample data
        Product product = new Product();
        product.setName("The Great Gatsby");

        // Call the save() method on the productService
        productService.save(product);

        // Verify that the productRepository.save() method was called with the correct parameter
        verify(productRepository).save(product);
    }
}
