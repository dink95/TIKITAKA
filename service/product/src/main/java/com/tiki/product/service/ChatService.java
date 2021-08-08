package com.tiki.product.service;

import com.tiki.product.domain.ChatDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatService {

    public int insertChat(ChatDTO params);

    public Integer findRoomNoSendId(ChatDTO params);

    public List<ChatDTO> selectAllChat(ChatDTO params);

    public ChatDTO selectChatDetail(int chatIdx);

    public List<ChatDTO> selectExistChatList(String sendId);

    public int selectChatTotalCount();

    // public Integer findRoomNoRecipientId(ChatDTO params);

}
