package com.example.Swiggato.Repository;
import com.example.Swiggato.Enum.FoodCategory;
import com.example.Swiggato.Model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem,Integer> {

    public List<FoodItem> findByfoodCategory(FoodCategory category);
}
