package com.clv.jwat.note2p.mapper;

import com.clv.jwat.note2p.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> getCategories(Long userId);

    void insert(Category category);

    int delete(Category category);

    int update(Category category);

    Category findById(Long id);

}