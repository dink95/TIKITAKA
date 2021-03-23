package com.tiki.product.mapper;

import com.tiki.product.domain.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    public int insertCategory(CategoryDTO params);

    public int deleteCategory(CategoryDTO params);

    public CategoryDTO selectCategoryDetail(int catNo);

    public List<CategoryDTO> selectCategoryList();

    public int selectCategoryTotalCount();

}
