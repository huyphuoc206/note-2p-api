package com.clv.jwat.note2p.api;

import com.clv.jwat.note2p.entity.Category;
import com.clv.jwat.note2p.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryAPI {

    private final ICategoryService iCategoryService;

    @GetMapping("/users/{userId}/categories")
    public ResponseEntity<List<Category>> getCategories(@PathVariable ("userId") Long userId)
    {
        List<Category> categories = iCategoryService.getCategories(userId);
        if(categories != null)
        {
            log.info("get category {} success", userId);
            return ResponseEntity.ok(categories);
        }else {
            log.error("get category {} fail", userId);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable ("id") Long id)
    {
        Category result = iCategoryService.getCategory(id);
        if(result != null)
        {
            log.info("get category {} success", id);
            return ResponseEntity.ok(result);
        }else {
            log.error("get category {} fail", id);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> insert(@RequestBody Category category)
    {
        Category result = iCategoryService.insert(category);
        if(result != null)
        {
            log.info("insert category {} success");
            return ResponseEntity.ok(result);
        }else
        {
            log.error("insert category {} fail");
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category)
    {
        Category result = iCategoryService.update(id, category);
        if(result != null)
        {
            log.info("update category {} success", id);
            return ResponseEntity.ok(result);
        }else
        {
            log.error("update category {} fail", id);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id)
    {
        System.out.println("dsadsa");
        boolean result = iCategoryService.delete(id);
        if(result)
        {
            log.info("delete category {} success", id);
            return ResponseEntity.ok(true);
        }else
        {
            log.error("delete category {} fail", id);
            return ResponseEntity.badRequest().body(false);
        }
    }
}
