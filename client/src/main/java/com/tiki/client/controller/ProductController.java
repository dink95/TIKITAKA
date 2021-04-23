package com.tiki.client.controller;

import com.tiki.client.domain.MemberDTO;
import com.tiki.client.domain.ProductDTO;
import com.tiki.client.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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
    public Map<String, Object> createProduct(ProductDTO productDTO, MultipartHttpServletRequest multi) {

        Map<String, Object> resultMap = new HashMap<>();


        int result = 0;

        try {
            List<MultipartFile> fileList = multi.getFiles("file");
            productDTO.setFiles(fileList);
            result = productService.createProduct(productDTO);

        } catch (Exception e) {

        }
        System.out.println(productDTO);

        return resultMap;
    }

    @GetMapping(value = "/product/list") /*상품 상세 페이지*/
    public ModelAndView list() {
        ModelAndView view = new ModelAndView();
        view.setViewName("product/list");
        return view;
    }
}

