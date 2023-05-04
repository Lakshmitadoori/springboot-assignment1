package spring.myapplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.myapplication.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    public Category findByCategoryName(String name);

}
