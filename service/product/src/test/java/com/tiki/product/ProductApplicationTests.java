package com.tiki.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.tiki.product.domain.ProductDTO;
import com.tiki.product.mapper.ProductMapper;
import com.tiki.product.service.ProductService;

import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class ProductApplicationTests {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @Test
    public void testOfUpdateView() {

        ///int result = productMapper.updateView(1);

        ProductDTO productDTO = productService.selectProductDetail(1);

        System.out.println("=============");
        System.out.println(productDTO.getProdNo());
        System.out.println(productDTO.getProdNm());
        System.out.println(productDTO.getProdView());
        System.out.println("=============");

    }

    /**@Test
    public void testOfSelectDetail() {
        int productTotalCount = productMapper.selectProductTotalCount();
        if(productTotalCount > 0) {
            List<ProductDTO> productList = productMapper.selectProductList();
            if(CollectionUtils.isEmpty(productList) == false) {
                for(ProductDTO product : productList) {
                    System.out.println("==============");
                    System.out.println(product.getProdNm());
                    System.out.println(product.getSelId());
                    System.out.println(product.getProdPrc());
                    System.out.println("==============");
                }
            }
        }
    }*/

}
