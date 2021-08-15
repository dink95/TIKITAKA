package com.tiki.product.service;

import com.tiki.product.domain.ChatDTO;
import com.tiki.product.mapper.ChatMapper;
import com.tiki.product.domain.ProductDTO;
import com.tiki.product.mapper.ProductMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Integer findRoomNoSendId(ChatDTO params) {
        return chatMapper.findRoomNoSendId(params);
    }

    /**@Override
    public Integer findRoomNoRecipientId(ChatDTO params) {
        return chatMapper.findRoomNoRecipientId(params);
    }*/

    @Override
    public int insertChat(ChatDTO params) {
        return chatMapper.insertChat(params);
    }

    @Override
    public List<ChatDTO> selectAllChat(ChatDTO params) {

        return chatMapper.selectAllChat(params);
    }

    @Override
    public ChatDTO selectChatDetail(int chatIdx) {

        return chatMapper.selectChatDetail(chatIdx);
    }

    @Override
    public List<ChatDTO> selectExistChatList(String sendId) {

        return chatMapper.selectExistChatList(sendId);
    }


    @Override
    public int selectChatTotalCount() {

        return chatMapper.selectChatTotalCount();
    }



    @Override
    public int updateViewChat(ChatDTO params) {

        return chatMapper.updateViewChat(params);
    }


    @Override
    public int selectReadCount(ChatDTO chatDTO) {

        return chatMapper.selectReadCount(chatDTO);
    }


}
