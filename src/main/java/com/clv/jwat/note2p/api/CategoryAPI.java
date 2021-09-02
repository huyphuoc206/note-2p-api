package com.clv.jwat.note2p.api;

import com.clv.jwat.note2p.api.response.CategoryResponse;
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

    private final ICategoryService categoryService;

    @GetMapping("/users/{userId}/categories")
    public ResponseEntity<CategoryResponse> getCategories(@PathVariable("userId") Long userId, @RequestParam(value = "page", defaultValue = "1") int page,
                                                        @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Category> categories = categoryService.getCategories(userId, page, limit);
        long records = categoryService.count(userId);
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategories(categories);
        categoryResponse.setRecords(records);
        if (categories != null) {
            log.info("get list of categories user {} success.", userId);
            return ResponseEntity.ok(categoryResponse);
        } else {
            log.error("get list of categories user {} fail", userId);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long id) {
        Category result = categoryService.getCategory(id);
        if (result != null) {
            log.info("get category {} success", id);
            return ResponseEntity.ok(result);
        } else {
            log.error("get category {} fail", id);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> insert(@RequestBody Category category) {
        Category result = categoryService.insert(category);
        if (result != null) {
            log.info("insert category {} success");
            return ResponseEntity.ok(result);
        } else {
            log.error("insert category {} fail");
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Category result = categoryService.update(id, category);
        if (result != null) {
            log.info("update category {} success", id);
            return ResponseEntity.ok(result);
        } else {
            log.error("update category {} fail", id);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        boolean result = categoryService.delete(id);
        if (result) {
            log.info("delete category {} success", id);
            return ResponseEntity.ok(true);
        } else {
            log.error("delete category {} fail", id);
            return ResponseEntity.badRequest().body(false);
        }
    }
}
