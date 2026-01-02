package com.example.productservice.repository;

import com.example.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    /*
    JPA method - behind the scenes, implementation will
    be done by JPA on your behalf
    select * from category where name = 'xyz'
     */

    @Override
    void deleteById(Long id);
}
