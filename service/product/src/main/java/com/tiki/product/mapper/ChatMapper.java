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

    public List<ChatDTO> selectExistChatList(String sendId);



    public int selectChatTotalCount();

    public int updateChatReadCount(ChatDTO chatDTO);

    public int updateViewChat(ChatDTO chatDTO);




    public int selectReadCount(ChatDTO chatDTO);

    public int selectAllReadCount(ChatDTO chatDTO);

    public int selectSendCount(ChatDTO chatDTO);

    // public Integer findRoomNoRecipientId(ChatDTO params);
}