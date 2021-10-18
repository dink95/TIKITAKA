package com.tiki.product.service;

import com.tiki.product.domain.BuylistDTO;
import com.tiki.product.mapper.BuylistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BuylistServiceImpl implements BuylistService {

    @Autowired
    private BuylistMapper buylistMapper;

    @Override
    public int insertBuylist(BuylistDTO params) {

        return buylistMapper.insertBuylist(params);
    }

    @Override
    public int deleteBuylist(BuylistDTO params) {

        return buylistMapper.deleteBuylist(params);
    }

    @Override
    public BuylistDTO getBuylistDetail(String buyerId) {

        return buylistMapper.selectBuylistDetail(buyerId);
    }

    @Override
    public List<BuylistDTO> getBuylistList(String buyerId) {

        List<BuylistDTO> buylistList = Collections.emptyList();

        int buylistTotalCount = buylistMapper.selectBuylistTotalCount();

        if (buylistTotalCount > 0) {
            buylistList = buylistMapper.selectBuylistList(buyerId);
        }

        return buylistList;
    }
}
