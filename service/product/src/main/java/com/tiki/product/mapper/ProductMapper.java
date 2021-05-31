package com.tiki.product.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.tiki.product.domain.ProductDTO;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {

    public int insertProduct(ProductDTO params);

    public int updateProduct(ProductDTO params);

    public int updateProductFinish(ProductDTO params);

    public int deleteProduct(ProductDTO params);

    public int selectProductNo(ProductDTO params);

    public ProductDTO selectProductDetail(int prodNo);

    public int updateView(int prodNo);

    public List<ProductDTO> selectProductList();

    public List<ProductDTO> resultByNmCat(ProductDTO params);

    public List<ProductDTO> resultByProdNm(String prodNm);

    public List<ProductDTO> resultById(String selId);

    public List<ProductDTO> resultByCatNo(@Param("firstNo") int firstNo, @Param("lastNo") int lastNo);

    public int selectProductTotalCount();

}
