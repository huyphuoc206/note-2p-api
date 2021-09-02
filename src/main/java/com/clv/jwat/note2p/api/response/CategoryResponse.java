package com.clv.jwat.note2p.api.response;

import com.clv.jwat.note2p.entity.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private List<Category> categories;
    private long records;
}
