package spring.myapplication.service;



import spring.myapplication.entity.Category;

import java.util.List;


public interface CategoryService {
    public List<Category> findAll();

    public Category findById(int theId);

    public void save(String category);

    public void deleteById(int theId);

    public Category findByName(String name);

}
