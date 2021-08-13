package com.tiki.product.service;

import com.tiki.product.domain.ChatDTO;
import com.tiki.product.domain.ProductDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatService {

    public int insertChat(ChatDTO params);

    public Integer findRoomNoSendId(ChatDTO params);

    public List<ChatDTO> selectAllChat(ChatDTO params);

    public ChatDTO selectChatDetail(int chatIdx);

    public List<ChatDTO> selectExistChatList(String sendId);

    public ChatDTO selectLastChatId(ChatDTO params);

    public int selectChatTotalCount();

    public List<ChatDTO> selectChatReadCountById(ChatDTO params);

    public int selectChatReadCountByProdNoRoomNo(ChatDTO params);

    public int updateChatReadCount(ChatDTO params);

    public int updateViewChat(ChatDTO params);

    public int selectReadCount(ChatDTO chatDTO);
    // public Integer findRoomNoRecipientId(ChatDTO params);

}
