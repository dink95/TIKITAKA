package com.tiki.product.service;

import com.tiki.product.domain.BuylistDTO;

import java.util.List;

public interface BuylistService {

    public int insertBuylist(BuylistDTO params);

    public int deleteBuylist(BuylistDTO params);

    public BuylistDTO getBuylistDetail(String buyerId);

    public List<BuylistDTO> getBuylistList();

}
