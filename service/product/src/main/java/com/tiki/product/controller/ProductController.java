package com.tiki.product.controller;

import com.tiki.product.domain.ProductDTO;
import com.tiki.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/prd/{prodNo}")
    public ProductDTO selectProductByProdNo(@PathVariable("prodNo") int prodNo) {
        return productService.selectProductDetail(prodNo);
    }

    @PostMapping("/prd")
    public int insertProduct(@RequestBody ProductDTO productDTO) {
        return productService.insertProduct(productDTO);
    }


}
