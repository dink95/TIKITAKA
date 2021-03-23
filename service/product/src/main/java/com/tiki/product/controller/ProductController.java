package com.tiki.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tiki.product.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

}
