package com.tiki.product.service;

import java.util.List;
import com.tiki.product.domain.ProductDTO;

public interface ProductService {

    public int insertProduct(ProductDTO params);

    public boolean updateProduct(ProductDTO params);

    public int deleteProduct(ProductDTO params);

    public ProductDTO selectProductDetail(int prodNo);

    public List<ProductDTO> getProductList();

}
