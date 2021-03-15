package com.tiki.product.service;

import com.tiki.product.domain.CategoryDTO;
import com.tiki.product.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int insertCategory(CategoryDTO params) {

        return queryResult = categoryMapper.insertCategory(params);
    }

    @Override
    public int deleteCategory(CategoryDTO params) {

        return categoryMapper.deleteCategory(params);
    }

    @Override
    public CategoryDTO getCategoryDetail(int catNo) {

        return categoryMapper.selectCategoryDetail(catNo);
    }

    @Override
    public List<CategoryDTO> getCategoryList() {

        List<CategoryDTO> categoryList = Collections.emptyList();

        int categoryTotalCount = categoryMapper.selectCategoryTotalCount();

        if (categoryTotalCount > 0) {
            categoryList = categoryMapper.selectCategoryList();
        }

        return categoryList;
    }
}
