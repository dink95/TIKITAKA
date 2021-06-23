package com.tiki.product.service;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.annotations.Param;
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
    public int updateProduct(ProductDTO params) {

        return productMapper.updateProduct(params);
    }

    @Override
    public int updateProductFinish(ProductDTO params) {

        return productMapper.updateProductFinish(params);
    }

    @Override
    public ProductDTO selectProductDetail(int prodNo) {

        productMapper.updateView(prodNo);

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

    @Override
    public List<ProductDTO> resultByNmCat(ProductDTO params) {

        List<ProductDTO> productList = Collections.emptyList();

        int productTotalCount = productMapper.selectProductTotalCount();

        if (productTotalCount > 0) {
            productList = productMapper.resultByNmCat(params);
        }

        return productList;
    }

    @Override
    public List<ProductDTO> resultByProdNm(String prodNm) {

        List<ProductDTO> productList = Collections.emptyList();

        int productTotalCount = productMapper.selectProductTotalCount();

        if (productTotalCount > 0) {
            productList = productMapper.resultByProdNm(prodNm);
        }

        return productList;
    }

    @Override
    public List<ProductDTO> resultById(String catNm) {

        List<ProductDTO> productList = Collections.emptyList();

        int productTotalCount = productMapper.selectProductTotalCount();

        if (productTotalCount > 0) {
            productList = productMapper.resultById(catNm);
        }

        return productList;
    }

    @Override
    public List<ProductDTO> resultByIdFinish(String catNm) {

        List<ProductDTO> productList = Collections.emptyList();

        int productTotalCount = productMapper.selectProductTotalCount();

        if (productTotalCount > 0) {
            productList = productMapper.resultByIdFinish(catNm);
        }

        return productList;
    }

    @Override
    public List<ProductDTO> resultByCatNo(@Param("firstNo") int firstNo, @Param("lastNo") int lastNo) {

        List<ProductDTO> productList = Collections.emptyList();

        int productTotalCount = productMapper.selectProductTotalCount();

        if (productTotalCount > 0) {
            productList = productMapper.resultByCatNo(firstNo, lastNo);
        }

        return productList;
    }

    @Override
    public int selectProductNo(ProductDTO params) {
        return productMapper.selectProductNo(params);
    }
}
