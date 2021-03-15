package com.tiki.product.service;

import com.tiki.product.domain.CategoryDTO;

import java.util.List;

public interface CategoryService {

    public int insertCategory(CategoryDTO params);

    public int deleteCategory(CategoryDTO params);

    public CategoryDTO getCategoryDetail(int catNo);

    public List<CategoryDTO> getCategoryList();

}
