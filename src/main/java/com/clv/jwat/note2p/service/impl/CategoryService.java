package com.clv.jwat.note2p.service.impl;

import com.clv.jwat.note2p.entity.Category;
import com.clv.jwat.note2p.entity.Task;
import com.clv.jwat.note2p.mapper.CategoryMapper;
import com.clv.jwat.note2p.mapper.TaskMapper;
import com.clv.jwat.note2p.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryMapper categoryMapper;

    private final TaskMapper taskMapper;


    @Override
    public List<Category> getCategories(Long userId) {
        List<Category> results = categoryMapper.getCategories(userId);
        if (results == null) {
            return null;
        }
        return results;
    }

    @Transactional
    @Override
    public Category insert(Category category) {
        category.setDeleted(false);
        category.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        category.setUserId(category.getUserId());
        categoryMapper.insert(category);
        if (category.getId() == null) {
            return null;
        }
        List<Task> tasks = category.getTasks();
        for (Task items : tasks) {
            items.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            items.setDeleted(false);
            items.setCategoryId(category.getId());
            items.setDone(false);
            taskMapper.save(items);
        }
        return categoryMapper.findById(category.getId());
    }

    @Override
    public boolean delete(Long id) {
        Category category = categoryMapper.findById(id);
        if (category == null)
            return false;
        category.setDeleted(true);
        category.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        int result = categoryMapper.delete(category);
        return result > 0;
    }

    @Override
    public Category update(Long categoryID, Category category) {
        Category oldCategory = categoryMapper.findById(categoryID);
        if (oldCategory == null)
            return null;
        category.setId(categoryID);
        category.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        int result = categoryMapper.update(category);
        if(result <= 0)
        {
            return null;
        }
        else
        {
            return categoryMapper.findById(categoryID);
        }
    }

    @Override
    public Category getCategory(Long id) {
        Category result = categoryMapper.findById(id);
        if( result == null)
        {
            return null;
        }
        List<Task> tasks = taskMapper.findByCategoryId(id);
        result.setTasks(tasks);

        return result;
    }
}
