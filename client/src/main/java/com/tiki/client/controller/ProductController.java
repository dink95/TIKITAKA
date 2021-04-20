package com.tiki.client.controller;

import com.tiki.client.domain.MemberDTO;
import com.tiki.client.domain.ProductDTO;
import com.tiki.client.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/product/create") /*상품등록 페이지*/
    public ModelAndView create() {
        ModelAndView view = new ModelAndView();
        view.setViewName("product/create");
        return view;
    }

    @RequestMapping("/product/create.do")  /*상품등록*/
    @ResponseBody
    public Map<String, Object> createProduct(@RequestBody ProductDTO productDTO) {

        Map<String, Object> resultMap = new HashMap<>();

        System.out.println(productDTO);

        int result = 0;

            try {
            result = productService.createProduct(productDTO);


            if (result > 0) {
                resultMap.put("resultCode", 200);
                resultMap.put("resultMsg", "ok");
            } else {
                resultMap.put("resultCode", 400);
                resultMap.put("resultMsg", "400 error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 500);
            resultMap.put("resultMsg", "500error.");
        }


        return resultMap;
    }


    @GetMapping(value = "/product/view") /*상품등록 페이지*/
    public ModelAndView view() {
        ModelAndView view = new ModelAndView();
        view.setViewName("product/view");
        return view;
    }
}

