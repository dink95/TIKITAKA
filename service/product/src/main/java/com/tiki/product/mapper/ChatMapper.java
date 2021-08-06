package com.tiki.product.mapper;

import java.util.List;

import com.tiki.product.domain.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import com.tiki.product.domain.ChatDTO;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChatMapper {

    public int insertChat(ChatDTO params);

    public Integer findRoomNoSendId(ChatDTO params);

    public List<ChatDTO> selectAllChat(ChatDTO params);

    public ChatDTO selectChatDetail(int chatIdx);

    // public Integer findRoomNoRecipientId(ChatDTO params);
}