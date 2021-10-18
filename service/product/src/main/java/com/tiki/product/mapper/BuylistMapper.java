package com.tiki.product.mapper;

import com.tiki.product.domain.BuylistDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BuylistMapper {

    public int insertBuylist(BuylistDTO params);

    public int deleteBuylist(BuylistDTO params);

    public BuylistDTO selectBuylistDetail(String buyerId);

    public List<BuylistDTO> selectBuylistList(String buyerId);

    public int selectBuylistTotalCount();

}
