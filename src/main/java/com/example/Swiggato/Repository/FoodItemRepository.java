package com.example.Swiggato.Repository;
import com.example.Swiggato.Enum.FoodCategory;
import com.example.Swiggato.Model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<MenuItem,Integer> {

    public List<MenuItem> findByfoodCategory(FoodCategory category);
}
