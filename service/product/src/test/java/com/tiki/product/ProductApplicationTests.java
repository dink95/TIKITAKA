package com.tiki.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.tiki.product.domain.ProductDTO;
import com.tiki.product.mapper.ProductMapper;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class ProductApplicationTests {

    @Autowired
    private ProductMapper productMapper;

    /*@Test
    public void testOfInsert() {
        ProductDTO params = new ProductDTO();
        params.setProdNm("테스트");
        params.setProdPrc(2500);
        params.setCatNo(123);
        params.setSelId("94dglee");
        params.setWay("직거래");
        params.setWday(LocalDateTime.now());
        params.setProdCo("1423");

        int result = productMapper.insertProduct(params);
        System.out.println("결과는 " + result + "입니다.");

    }*/

    @Test
    public void testOfSelectDetail() {
        int productTotalCount = productMapper.selectProductTotalCount();
        if(productTotalCount > 0) {
            List<ProductDTO> productList = productMapper.resultByCatNm("여성의류");
            if(CollectionUtils.isEmpty(productList) == false) {
                for(ProductDTO product : productList) {
                    System.out.println("==============");
                    System.out.println(product.getProdNm());
                    System.out.println(product.getSelId());
                    System.out.println(product.getProdPrc());
                    System.out.println(product.getCatNo());
                    System.out.println("==============");
                }
            }
        }
    }

}
