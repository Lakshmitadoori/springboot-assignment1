package spring.myapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.myapplication.entity.Category;
import spring.myapplication.entity.Product;
import spring.myapplication.service.CategoryService;
import spring.myapplication.service.ProductService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    // add mapping for "/list"

    public ProductController(ProductService productService) {
       this.productService = productService;
    }
    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        List<Product>  productList = productService.findAll();
        // add to the spring model
        theModel.addAttribute("products", productList);

        return "products/list-products";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Product product = new Product();

        theModel.addAttribute("product",product);

        return "products/product-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("product") Product theProduct, @RequestParam("category") String categoryName) {

        categoryService.save(categoryName);
        Category category = categoryService.findByName(categoryName);

        Set<Category> categories = new HashSet<>();
        categories.add(category);
        theProduct.setCategories(categories);
        // save the employee
        productService.save(theProduct);


        // use a redirect to prevent duplicate submissions
        return "redirect:/products/list";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("productId") int theId,
                                    Model theModel) {

        // get the employee from the service
        Product theproduct = productService.findById(theId);

        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("product", theproduct);

        // send over to our form
        return "products/product-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("productId") int theId) {

        // delete the employee
        productService.deleteById(theId);

        // redirect to /employees/list
        return "redirect:/products/list";

    }
}
