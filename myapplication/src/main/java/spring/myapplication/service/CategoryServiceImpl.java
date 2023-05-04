package spring.myapplication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.myapplication.entity.Category;
import spring.myapplication.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    @Override
    public Category findById(int theId) {
        Optional<Category> result = categoryRepository.findById(theId);

        Category category = null;

        if (result.isPresent()) {
            category = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find product id - " + theId);
        }

        return category;
    }

    @Override
    public void save (String category){
        Category category1 = new Category(category);
        categoryRepository.save(category1);
    }
    @Override
    public void deleteById(int theId) {
        categoryRepository.deleteById(theId);

    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByCategoryName(name);
    }


}
