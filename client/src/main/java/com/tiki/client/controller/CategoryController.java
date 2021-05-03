package com.tiki.client.controller;

import com.tiki.client.domain.CategoryDTO;
import com.tiki.client.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CategoryController {

   @Autowired
   private CategoryService categoryService;

    @RequestMapping("/category/get")  /*카테고리조회*/
    @ResponseBody
    public Map<String,Object> getCategory(@RequestParam(value = "catNo") Integer catNo) {
       Map<String,Object> resultMap = new HashMap<>();

        try {
            CategoryDTO categoryDTO = categoryService.getCategory(catNo);
            System.out.println(categoryDTO);
            resultMap.put("categoryDTO",categoryDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

}
