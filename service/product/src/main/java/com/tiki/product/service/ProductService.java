package com.tiki.product.service;

import java.util.List;
import com.tiki.product.domain.ProductDTO;
import org.apache.ibatis.annotations.Param;

public interface ProductService {

    public int insertProduct(ProductDTO params);

    public int updateProduct(ProductDTO params);

    public int deleteProduct(ProductDTO params);

    public int updateProductFinish(ProductDTO params);

    public ProductDTO selectProductDetail(int prodNo);

    public List<ProductDTO> getProductList();

    public List<ProductDTO> resultByNmCat(ProductDTO params);

    public List<ProductDTO> resultByNmCatHighPrice(ProductDTO params);

    public List<ProductDTO> resultByNmCatLowPrice(ProductDTO params);

    public List<ProductDTO> resultByProdNm(String prodNm);

    public List<ProductDTO> resultByProdNmHighPrice(String prodNm);

    public List<ProductDTO> resultByProdNmLowPrice(String prodNm);

    public List<ProductDTO> resultById(String selId);

    public List<ProductDTO> resultByIdFinish(String selId);

    public List<ProductDTO> resultByCatNo(@Param("firstNo") int firstNo, @Param("lastNo") int lastNo);

    public List<ProductDTO> resultByCatNoHighPrice(@Param("firstNo") int firstNo, @Param("lastNo") int lastNo);

    public List<ProductDTO> resultByCatNoLowPrice(@Param("firstNo") int firstNo, @Param("lastNo") int lastNo);

    public int selectProductNo(ProductDTO params);

}
