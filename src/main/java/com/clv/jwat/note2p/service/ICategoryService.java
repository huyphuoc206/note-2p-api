package com.clv.jwat.note2p.service;

import com.clv.jwat.note2p.entity.Category;
import com.clv.jwat.note2p.entity.Task;

import java.util.List;

public interface ICategoryService {

    List<Category> getCategories(Long userId);

    Category insert(Category category);

    boolean delete(Long id);

    Category update(Long categoryID, Category category);

    Category getCategory(Long id);
}
