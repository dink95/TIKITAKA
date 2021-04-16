package com.tiki.product.controller;

import com.tiki.product.domain.CategoryDTO;
import com.tiki.product.domain.ProductDTO;
import com.tiki.product.service.CategoryService;
import com.tiki.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 카테고리 상세보기
    @GetMapping("/cat/{catNo}")
    public CategoryDTO selectCategoryByCatNo(@PathVariable("catNo") int catNo) {
        return categoryService.getCategoryDetail(catNo);
    }

    // 전체 카테고리 보기
    @GetMapping(value = "/cat/list")
    public List categoryList() {

        List<CategoryDTO> categoryList = categoryService.getCategoryList();

        return categoryList;
    }

}
