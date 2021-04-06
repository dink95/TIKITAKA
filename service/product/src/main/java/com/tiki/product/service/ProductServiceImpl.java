package com.tiki.product.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiki.product.domain.ProductDTO;
import com.tiki.product.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public int insertProduct(ProductDTO params) {

        return productMapper.insertProduct(params);
    }

    @Override
    public boolean updateProduct(ProductDTO params) {

        int queryResult = 0;

        queryResult = productMapper.updateProduct(params);

        return (queryResult == 1) ? true : false;
    }

    @Override
    public ProductDTO selectProductDetail(int prodNo) {
        return productMapper.selectProductDetail(prodNo);
    }

    @Override
    public int deleteProduct(ProductDTO params) {

        return productMapper.deleteProduct(params);
    }

    @Override
    public List<ProductDTO> getProductList() {

        List<ProductDTO> productList = Collections.emptyList();

        int productTotalCount = productMapper.selectProductTotalCount();

        if (productTotalCount > 0) {
            productList = productMapper.selectProductList();
        }

        return productList;
    }
}
