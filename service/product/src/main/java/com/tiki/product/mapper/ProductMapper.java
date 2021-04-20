package com.tiki.product.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.tiki.product.domain.ProductDTO;

@Mapper
public interface ProductMapper {

    public int insertProduct(ProductDTO params);

    public int updateProduct(ProductDTO params);

    public int deleteProduct(ProductDTO params);

    public ProductDTO selectProductDetail(int prodNo);

    public List<ProductDTO> selectProductList();

    public List<ProductDTO> resultByNmCat(ProductDTO params);

    public int selectProductTotalCount();

}
